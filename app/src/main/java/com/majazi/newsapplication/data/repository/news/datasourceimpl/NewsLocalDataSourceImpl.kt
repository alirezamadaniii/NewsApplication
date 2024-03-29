package com.majazi.newsapplication.data.repository.news.datasourceimpl

import com.majazi.newsapplication.data.db.NewsDao
import com.majazi.newsapplication.data.model.Category
import com.majazi.newsapplication.data.model.DataSavedList
import com.majazi.newsapplication.data.model.detailnews.comment.SignInUser
import com.majazi.newsapplication.data.model.homenews.ItemNews
import com.majazi.newsapplication.data.model.newslist.Data
import com.majazi.newsapplication.data.model.trendingnews.Post
import com.majazi.newsapplication.data.repository.news.datasource.NewsLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NewsLocalDataSourceImpl(
    private val dao: NewsDao
):NewsLocalDataSource {
    override suspend fun saveNewsToDB(data: List<Data>) {
        dao.saveNews(data)
    }

    override suspend fun deleteNewsToDB() {
        return dao.deleteAllNews()
    }


    override suspend fun getNewsFromDb(catId:String): List<Data> {
       return dao.getNewsList(catId)
    }

    override suspend fun getSaveNews():Flow<List<DataSavedList>> {
           return dao.getSavedNews()
    }

    override suspend fun saveNewsToSaved(data: DataSavedList) {
        return dao.saveNewsToSaved(data)
    }

    override suspend fun saveCategoryToDb(itemNews: List<ItemNews>) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.saveCategoryHomePage(itemNews)
        }
    }
    override suspend fun getCategoryFromDb(): List<ItemNews> {
        return dao.getCategoryList()
    }

    override suspend fun deleteNewsList() {
        return dao.deleteNewsList()
    }

    override suspend fun deleteNews(data: DataSavedList) {
        return dao.deleteNews(data)
    }



    override suspend fun saveTrendingNewsToDb(data: List<Post>) {
        return dao.saveTrendingNews(data)
    }

    override suspend fun getTrendingNews(): List<Post> {
        return dao.getTradingNewsFromDb()
    }

    override suspend fun deleteTrendingNews() {
        return dao.deleteTradingNewsFromDb()
    }

    override suspend fun signInUser(signInUser: SignInUser) {
        return dao.signInUser(signInUser)
    }

    override fun getUser(): Flow<SignInUser> {
        return dao.getUser()
    }

    override suspend fun addCounter(category: Category) {
        return dao.addCounter(category)
    }

    override suspend fun getCounter(categoryId: Int):Flow<Int> {
        return dao.getCounter(categoryId)
    }

    override suspend fun getAllCounter(): Flow<List<Category>> {
        return dao.getAllCounter()
    }


}
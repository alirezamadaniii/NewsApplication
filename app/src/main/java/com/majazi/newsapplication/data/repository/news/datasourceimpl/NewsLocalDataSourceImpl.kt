package com.majazi.newsapplication.data.repository.news.datasourceimpl

import com.majazi.newsapplication.data.db.NewsDao
import com.majazi.newsapplication.data.model.homenews.ItemNews
import com.majazi.newsapplication.data.model.newslist.Data
import com.majazi.newsapplication.data.repository.news.datasource.NewsLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NewsLocalDataSourceImpl(
    private val dao: NewsDao
):NewsLocalDataSource {
    override suspend fun saveNewsToDB(data: Data) {
        dao.saveNews(data)
    }



    override suspend fun getNewsFromDb(): Flow<List<Data>> {
       return dao.getNewsList()
    }

    override suspend fun saveCategoryToDb(itemNews: List<ItemNews>) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.saveCategoryHomePage(itemNews)
        }
    }
    override suspend fun getCategoryFromDb(): List<ItemNews> {
        return dao.getCategoryList()
    }

    override suspend fun deleteNews(data: Data) {
        return dao.deleteNews(data)
    }

}
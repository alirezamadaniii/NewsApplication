package com.majazi.newsapplication.data.repository.news.datasource

import com.majazi.newsapplication.data.model.DataSavedList
import com.majazi.newsapplication.data.model.detailnews.comment.SignInUser
import com.majazi.newsapplication.data.model.homenews.ItemNews
import com.majazi.newsapplication.data.model.newslist.Data
import com.majazi.newsapplication.data.model.trendingnews.Post
import com.majazi.newsapplication.data.model.trendingnews.TNews
import kotlinx.coroutines.flow.Flow

interface NewsLocalDataSource {
    suspend fun saveNewsToDB(data: List<Data>)
    suspend fun getNewsFromDb(catId:String):List<Data>

    suspend fun getSaveNews(): Flow<List<DataSavedList>>

    suspend fun saveNewsToSaved(data: DataSavedList)

    suspend fun saveCategoryToDb(itemNews:List<ItemNews>)

    suspend fun getCategoryFromDb():List<ItemNews>

    suspend fun deleteNews(data: DataSavedList)

    suspend fun saveTrendingNewsToDb(data: List<Post>)

    suspend fun getTrendingNews():List<Post>

    suspend fun signInUser(signInUser: SignInUser)

    fun getUser():Flow<SignInUser>

}
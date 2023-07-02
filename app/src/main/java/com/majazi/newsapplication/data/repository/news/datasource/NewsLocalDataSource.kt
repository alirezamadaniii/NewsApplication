package com.majazi.newsapplication.data.repository.news.datasource

import com.majazi.newsapplication.data.model.homenews.ItemNews
import com.majazi.newsapplication.data.model.newslist.Data
import com.majazi.newsapplication.data.model.trendingnews.Post
import com.majazi.newsapplication.data.model.trendingnews.TNews

interface NewsLocalDataSource {
    suspend fun saveNewsToDB(data: List<Data>)
    suspend fun getNewsFromDb():List<Data>

    suspend fun saveCategoryToDb(itemNews:List<ItemNews>)

    suspend fun getCategoryFromDb():List<ItemNews>

    suspend fun deleteNews(data: Data)

    suspend fun saveTrendingNewsToDb(data: List<Post>)

    suspend fun getTrendingNews():List<Post>

}
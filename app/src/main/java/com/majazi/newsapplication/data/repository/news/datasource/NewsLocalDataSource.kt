package com.majazi.newsapplication.data.repository.news.datasource

import com.majazi.newsapplication.data.model.homenews.HomeNews
import com.majazi.newsapplication.data.model.homenews.ItemNews
import com.majazi.newsapplication.data.model.newslist.Data
import retrofit2.Response
import java.util.concurrent.Flow

interface NewsLocalDataSource {
    suspend fun saveNewsToDB(data: Data)
    suspend fun getNewsFromDb():kotlinx.coroutines.flow.Flow<List<Data>>

    suspend fun saveCategoryToDb(itemNews:List<ItemNews>)

    suspend fun getCategoryFromDb():List<ItemNews>

}
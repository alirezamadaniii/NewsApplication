package com.majazi.newsapplication.data.repository.news.datasource

import com.majazi.newsapplication.data.model.homenews.ItemNews
import com.majazi.newsapplication.data.model.newslist.Data
import java.util.concurrent.Flow

interface NewsLocalDataSource {
    suspend fun saveNewsToDB(data: Data)
    suspend fun getNewsFromDb():kotlinx.coroutines.flow.Flow<List<Data>>
}
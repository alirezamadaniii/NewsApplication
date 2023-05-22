package com.majazi.newsapplication.data.repository.news.datasource

import com.majazi.newsapplication.data.model.homenews.ItemNews
import com.majazi.newsapplication.data.model.newslist.Data

interface NewsLocalDataSource {
    suspend fun saveNewsToDB(data: Data)
}
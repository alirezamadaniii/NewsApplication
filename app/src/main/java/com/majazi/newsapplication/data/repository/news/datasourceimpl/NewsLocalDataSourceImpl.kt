package com.majazi.newsapplication.data.repository.news.datasourceimpl

import com.majazi.newsapplication.data.db.NewsDao
import com.majazi.newsapplication.data.model.homenews.ItemNews
import com.majazi.newsapplication.data.model.newslist.Data
import com.majazi.newsapplication.data.repository.news.datasource.NewsLocalDataSource

class NewsLocalDataSourceImpl(
    private val dao: NewsDao
):NewsLocalDataSource {
    override suspend fun saveNewsToDB(data: Data) {
        dao.saveNews(data)
    }
}
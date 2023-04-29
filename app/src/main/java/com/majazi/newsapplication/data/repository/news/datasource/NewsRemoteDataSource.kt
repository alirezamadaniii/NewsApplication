package com.majazi.newsapplication.data.repository.news.datasource

import com.majazi.newsapplication.data.model.homenews.HomeNews
import retrofit2.Response

interface NewsRemoteDataSource {
    suspend fun getNews():Response<HomeNews>
}
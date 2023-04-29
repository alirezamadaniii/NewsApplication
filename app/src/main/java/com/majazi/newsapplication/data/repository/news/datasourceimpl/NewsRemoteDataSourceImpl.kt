package com.majazi.newsapplication.data.repository.news.datasourceimpl

import com.majazi.newsapplication.data.api.ApiService
import com.majazi.newsapplication.data.model.homenews.HomeNews
import com.majazi.newsapplication.data.repository.news.datasource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(private val apiService: ApiService):NewsRemoteDataSource {
    override suspend fun getNews(): Response<HomeNews> {
        return apiService.getNewsHome("fa","f3J^cagO#tA#dhCifH7siqjD)gCZVy#i")
    }

}
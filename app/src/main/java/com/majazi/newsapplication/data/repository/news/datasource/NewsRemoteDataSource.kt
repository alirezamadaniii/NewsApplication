package com.majazi.newsapplication.data.repository.news.datasource

import com.majazi.newsapplication.data.model.detailnews.DetailNews
import com.majazi.newsapplication.data.model.homenews.HomeNews
import com.majazi.newsapplication.data.model.newslist.NewsList
import retrofit2.Response

interface NewsRemoteDataSource {
    suspend fun getNews():Response<HomeNews>

    suspend fun getNewsList(catId:String):Response<NewsList>

    suspend fun getDetailNews(id:String):Response<DetailNews>
}
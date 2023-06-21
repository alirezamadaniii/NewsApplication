package com.majazi.newsapplication.data.repository.news.datasourceimpl

import com.majazi.newsapplication.data.api.ApiService
import com.majazi.newsapplication.data.model.detailnews.DetailNews
import com.majazi.newsapplication.data.model.detailnews.comment.Comment
import com.majazi.newsapplication.data.model.homenews.HomeNews
import com.majazi.newsapplication.data.model.newslist.NewsList
import com.majazi.newsapplication.data.model.search.Search
import com.majazi.newsapplication.data.model.trendingnews.TrendingNews
import com.majazi.newsapplication.data.repository.news.datasource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(private val apiService: ApiService):NewsRemoteDataSource {
    override suspend fun getNews(): Response<HomeNews> {
        return apiService.getNewsHome("fa")
    }

    override suspend fun getNewsList(catId:String): Response<NewsList> {
        return apiService.getNewsList(catId,"fa")
    }

    override suspend fun getDetailNews(id: String): Response<DetailNews> {
        return apiService.getDetailNews(id)
    }

    override suspend fun getNewsFromSearch(search: String): Response<Search> {
        return apiService.getSearch(search)
    }

    override suspend fun getComment(id: String): Response<Comment> {
        return apiService.getComment(id)
    }

    override suspend fun getTrendingNews(): Response<TrendingNews> {
        return apiService.getTrendingNews()
    }

}
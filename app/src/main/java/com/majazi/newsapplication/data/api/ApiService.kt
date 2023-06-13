package com.majazi.newsapplication.data.api

import com.majazi.newsapplication.data.model.detailnews.DetailNews
import com.majazi.newsapplication.data.model.detailnews.comment.Comment
import com.majazi.newsapplication.data.model.homenews.HomeNews
import com.majazi.newsapplication.data.model.newslist.NewsList
import com.majazi.newsapplication.data.model.search.Search
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {


    @Headers("apiKey: f3J^cagO#tA#dhCifH7siqjD)gCZVy#i")
    @GET("discoverHomePage")
    suspend fun getNewsHome(
        @Query("lang") language: String
    ): Response<HomeNews>

    @Headers("apiKey: f3J^cagO#tA#dhCifH7siqjD)gCZVy#i")
    @GET("post-by-category/{cat-id}")
    suspend fun getNewsList(
        @Path(value = "cat-id", encoded = true) catId: String, @Query("lang") language: String
    ): Response<NewsList>


    @Headers("apiKey: f3J^cagO#tA#dhCifH7siqjD)gCZVy#i")
    @GET("detail/{id}")
    suspend fun getDetailNews(
        @Path(value = "id", encoded = true) id: String
    ): Response<DetailNews>


    @Headers("apiKey: f3J^cagO#tA#dhCifH7siqjD)gCZVy#i")
    @GET("search")
    suspend fun getSearch(
        @Query("search") search: String
    ): Response<Search>

    @Headers("apiKey: f3J^cagO#tA#dhCifH7siqjD)gCZVy#i")
    @GET("comments/{id}")
    suspend fun getComment(
        @Path(value = "id", encoded = true) id: String
    ): Response<Comment>

}
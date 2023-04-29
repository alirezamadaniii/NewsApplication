package com.majazi.newsapplication.data.api

import com.majazi.newsapplication.data.model.homenews.HomeNews
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {

    @GET("discoverHomePage")
    suspend fun getNewsHome(
        @Query("lang") language:String,
        @Header("apiKey") apiKey:String
    ):Response<HomeNews>
}
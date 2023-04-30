package com.majazi.newsapplication.data.api

import com.majazi.newsapplication.data.model.homenews.HomeNews
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @Headers("apiKey: f3J^cagO#tA#dhCifH7siqjD)gCZVy#i")
    @GET("discoverHomePage")
    suspend fun getNewsHome(
        @Query("lang") language:String):Response<HomeNews>
}
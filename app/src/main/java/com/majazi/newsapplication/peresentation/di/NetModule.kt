package com.majazi.newsapplication.peresentation.di

import com.majazi.newsapplication.data.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetModule {

    @Singleton
    @Provides
    fun provideRetrofit():Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://10.3.10.172/api/v10/")
            .build()
    }


    @Singleton
    @Provides
    fun provideNewsApiService(retrofit: Retrofit):ApiService{
        return retrofit.create(ApiService::class.java)
    }
}
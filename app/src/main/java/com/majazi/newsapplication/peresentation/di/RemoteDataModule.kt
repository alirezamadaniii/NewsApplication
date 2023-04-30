package com.majazi.newsapplication.peresentation.di

import com.majazi.newsapplication.data.api.ApiService
import com.majazi.newsapplication.data.repository.news.datasource.NewsRemoteDataSource
import com.majazi.newsapplication.data.repository.news.datasourceimpl.NewsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(apiService: ApiService):NewsRemoteDataSource{
        return NewsRemoteDataSourceImpl(apiService)
    }
}
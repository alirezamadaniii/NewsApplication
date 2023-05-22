package com.majazi.newsapplication.peresentation.di

import com.majazi.newsapplication.data.db.NewsDao
import com.majazi.newsapplication.data.repository.news.datasource.NewsLocalDataSource
import com.majazi.newsapplication.data.repository.news.datasourceimpl.NewsLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {


    @Singleton
    @Provides
    fun provideLocalDataSource(newsDao: NewsDao):NewsLocalDataSource{
        return NewsLocalDataSourceImpl(newsDao)
    }
}
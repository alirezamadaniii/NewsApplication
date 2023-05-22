package com.majazi.newsapplication.peresentation.di

import com.majazi.newsapplication.data.repository.news.NewsRepositoryImpl
import com.majazi.newsapplication.data.repository.news.datasource.NewsLocalDataSource
import com.majazi.newsapplication.data.repository.news.datasource.NewsRemoteDataSource
import com.majazi.newsapplication.domien.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideNewsRepository(
        newsRemoteDataSource: NewsRemoteDataSource,
    newsLocalDataSource: NewsLocalDataSource
    ):NewsRepository{
        return NewsRepositoryImpl(
            newsRemoteDataSource,
            newsLocalDataSource)
    }
}
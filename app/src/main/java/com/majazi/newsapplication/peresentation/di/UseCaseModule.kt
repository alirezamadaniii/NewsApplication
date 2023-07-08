package com.majazi.newsapplication.peresentation.di

import com.majazi.newsapplication.domien.repository.NewsRepository
import com.majazi.newsapplication.domien.usecase.DeleteNewsUseCase
import com.majazi.newsapplication.domien.usecase.GetCommentUseCase
import com.majazi.newsapplication.domien.usecase.GetDetailNewsUseCase
import com.majazi.newsapplication.domien.usecase.GetNewsFromDbUseCase
import com.majazi.newsapplication.domien.usecase.GetNewsListUseCase
import com.majazi.newsapplication.domien.usecase.GetNewsUseCase
import com.majazi.newsapplication.domien.usecase.GetSearchNewsUseCase
import com.majazi.newsapplication.domien.usecase.GetTrendingNewsUseCase
import com.majazi.newsapplication.domien.usecase.SaveNewsUseCase
import com.majazi.newsapplication.domien.usecase.SignInUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Singleton
    @Provides
    fun provideGetNewsUseCase(
        newsRepository: NewsRepository
    ):GetNewsUseCase{
        return GetNewsUseCase(newsRepository)
    }


    @Singleton
    @Provides
    fun provideGetNewsListUseCase(
        newsRepository: NewsRepository
    ):GetNewsListUseCase{
        return GetNewsListUseCase(newsRepository)
    }


    @Singleton
    @Provides
    fun provideGetDetailNewsUseCase(
        newsRepository: NewsRepository
    ):GetDetailNewsUseCase{
        return GetDetailNewsUseCase(newsRepository)
    }


    @Singleton
    @Provides
    fun provideSaveNewsUseCase(
        newsRepository: NewsRepository
    ):SaveNewsUseCase{
        return SaveNewsUseCase(newsRepository)
    }

    @Singleton
    @Provides
    fun provideGetSaveNewsUseCase(
        newsRepository: NewsRepository
    ):GetNewsFromDbUseCase{
        return GetNewsFromDbUseCase(newsRepository)
    }


    @Singleton
    @Provides
    fun provideSearchNewsUseCase(
        newsRepository: NewsRepository
    ):GetSearchNewsUseCase{
        return GetSearchNewsUseCase(newsRepository)
    }

    @Singleton
    @Provides
    fun provideCommentUseCase(
        newsRepository: NewsRepository
    ):GetCommentUseCase{
        return GetCommentUseCase(newsRepository)
    }

    @Singleton
    @Provides
    fun provideDeleteNewsUseCase(
        newsRepository: NewsRepository
    ):DeleteNewsUseCase{
        return DeleteNewsUseCase(newsRepository)
    }

    @Singleton
    @Provides
    fun provideTrendingNewsUseCase(
        newsRepository: NewsRepository
    ):GetTrendingNewsUseCase{
        return GetTrendingNewsUseCase(newsRepository)
    }

    @Singleton
    @Provides
    fun provideSignInUserUseCase(
        newsRepository: NewsRepository
    ):SignInUserUseCase{
        return SignInUserUseCase(newsRepository)
    }



}
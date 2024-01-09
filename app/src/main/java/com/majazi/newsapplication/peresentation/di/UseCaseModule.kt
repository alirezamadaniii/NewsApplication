package com.majazi.newsapplication.peresentation.di

import com.majazi.newsapplication.domien.repository.NewsRepository
import com.majazi.newsapplication.domien.usecase.DeleteNewsUseCase
import com.majazi.newsapplication.domien.usecase.GetAllCounterUseCase
import com.majazi.newsapplication.domien.usecase.GetAppIconUseCase
import com.majazi.newsapplication.domien.usecase.GetCategoryUseCase
import com.majazi.newsapplication.domien.usecase.GetCommentUseCase
import com.majazi.newsapplication.domien.usecase.GetDetailNewsUseCase
import com.majazi.newsapplication.domien.usecase.GetInterestPostsUseCase
import com.majazi.newsapplication.domien.usecase.GetNewsFromDbUseCase
import com.majazi.newsapplication.domien.usecase.GetNewsListUseCase
import com.majazi.newsapplication.domien.usecase.GetNewsUseCase
import com.majazi.newsapplication.domien.usecase.GetSearchNewsUseCase
import com.majazi.newsapplication.domien.usecase.GetTrendingNewsUseCase
import com.majazi.newsapplication.domien.usecase.GetUserAuthUseCase
import com.majazi.newsapplication.domien.usecase.GetUserUseCase
import com.majazi.newsapplication.domien.usecase.SaveCounterUseCase
import com.majazi.newsapplication.domien.usecase.SaveNewsUseCase
import com.majazi.newsapplication.domien.usecase.SendCommentUseCase
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

    @Singleton
    @Provides
    fun provideGetUserUseCase(
        newsRepository: NewsRepository
    ):GetUserUseCase{
        return GetUserUseCase(newsRepository)
    }


    @Singleton
    @Provides
    fun provideSendCommentUseCase(
        newsRepository: NewsRepository
    ):SendCommentUseCase{
        return SendCommentUseCase(newsRepository)
    }

    @Singleton
    @Provides
    fun provideAppIconUseCase(
        newsRepository: NewsRepository
    ):GetAppIconUseCase{
        return GetAppIconUseCase(newsRepository)
    }


    @Singleton
    @Provides
    fun provideUserAuthUseCase(
        newsRepository: NewsRepository
    ):GetUserAuthUseCase{
        return GetUserAuthUseCase(newsRepository)
    }

    @Singleton
    @Provides
    fun provideInterestPostCase(
        newsRepository: NewsRepository
    ):GetInterestPostsUseCase{
        return GetInterestPostsUseCase(newsRepository)
    }

    @Singleton
    @Provides
    fun provideAddCounterCase(
        newsRepository: NewsRepository
    ):SaveCounterUseCase{
        return SaveCounterUseCase(newsRepository)
    }


    @Singleton
    @Provides
    fun provideGetCounterCase(
        newsRepository: NewsRepository
    ):GetCategoryUseCase{
        return GetCategoryUseCase(newsRepository)
    }


    @Singleton
    @Provides
    fun provideGetAllCounterCase(
        newsRepository: NewsRepository
    ):GetAllCounterUseCase{
        return GetAllCounterUseCase(newsRepository)
    }
}
package com.majazi.newsapplication.peresentation.di

import android.app.Application
import com.majazi.newsapplication.domien.usecase.DeleteNewsUseCase
import com.majazi.newsapplication.domien.usecase.GetAppIconUseCase
import com.majazi.newsapplication.domien.usecase.GetCommentUseCase
import com.majazi.newsapplication.domien.usecase.GetDetailNewsUseCase
import com.majazi.newsapplication.domien.usecase.GetNewsFromDbUseCase
import com.majazi.newsapplication.domien.usecase.GetNewsListUseCase
import com.majazi.newsapplication.domien.usecase.GetNewsUseCase
import com.majazi.newsapplication.domien.usecase.GetSearchNewsUseCase
import com.majazi.newsapplication.domien.usecase.GetTrendingNewsUseCase
import com.majazi.newsapplication.domien.usecase.GetUserUseCase
import com.majazi.newsapplication.domien.usecase.SaveNewsUseCase
import com.majazi.newsapplication.domien.usecase.SendCommentUseCase
import com.majazi.newsapplication.domien.usecase.SignInUserUseCase
import com.majazi.newsapplication.peresentation.viewmodel.detailnews.DetailNewsFactory
import com.majazi.newsapplication.peresentation.viewmodel.home.NewsViewModelFactory
import com.majazi.newsapplication.peresentation.viewmodel.newslist.NewsListViewModelFactory
import com.majazi.newsapplication.peresentation.viewmodel.savenews.SaveNewsViewModelFactory
import com.majazi.newsapplication.peresentation.viewmodel.search.SearchNewsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun provideNewsViewModelFactory(
        application: Application,
        getNewsUseCase: GetNewsUseCase,
        getTrendingNewsUseCase: GetTrendingNewsUseCase,
        getAppIconUseCase: GetAppIconUseCase
    ): NewsViewModelFactory {
        return NewsViewModelFactory(
            application,
            getNewsUseCase,
            getTrendingNewsUseCase,
            getAppIconUseCase)
    }

    @Singleton
    @Provides
    fun provideNewsListViewModelFactory(
        application: Application,
        getNewsListUseCase: GetNewsListUseCase,
        saveNewsUseCase: SaveNewsUseCase
//        getNewsFromDbUseCase: GetNewsFromDbUseCase
    ): NewsListViewModelFactory {
        return NewsListViewModelFactory(
            application,
            getNewsListUseCase,
            saveNewsUseCase
//            getNewsFromDbUseCase
        )
    }

    @Singleton
    @Provides
    fun provideDetailNewsViewModelFactory(
        application: Application,
        getDetailNewsUseCase: GetDetailNewsUseCase,
        getCommentUseCase: GetCommentUseCase,
        signInUserUseCase: SignInUserUseCase,
        getUserUseCase: GetUserUseCase,
        sendCommentUseCase: SendCommentUseCase
    ): DetailNewsFactory {
        return DetailNewsFactory(
            application,
            getDetailNewsUseCase,
            getCommentUseCase,
            signInUserUseCase,
            getUserUseCase,
            sendCommentUseCase
        )
    }

    @Singleton
    @Provides
    fun provideSearchNewsViewModelFactory(
        application: Application,
        getSearchNewsUseCase: GetSearchNewsUseCase
    ): SearchNewsViewModelFactory {
        return SearchNewsViewModelFactory(
            application,
            getSearchNewsUseCase
        )
    }

    @Singleton
    @Provides
    fun provideSaveNewsViewModelFactory(
        deleteNewsUseCaseFactory: DeleteNewsUseCase,
        getNewsFromDbUseCase: GetNewsFromDbUseCase
    ): SaveNewsViewModelFactory {
        return SaveNewsViewModelFactory(
            deleteNewsUseCaseFactory,getNewsFromDbUseCase)
    }


}
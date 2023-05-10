package com.majazi.newsapplication.peresentation.di

import android.app.Application
import com.majazi.newsapplication.domien.usecase.GetDetailNews
import com.majazi.newsapplication.domien.usecase.GetNewsListUseCase
import com.majazi.newsapplication.domien.usecase.GetNewsUseCase
import com.majazi.newsapplication.peresentation.viewmodel.detailnews.DetailNewsFactory
import com.majazi.newsapplication.peresentation.viewmodel.home.NewsViewModelFactory
import com.majazi.newsapplication.peresentation.viewmodel.newslist.NewsListViewModelFactory
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
        getNewsUseCase: GetNewsUseCase
    ): NewsViewModelFactory {
        return NewsViewModelFactory(
            application,
            getNewsUseCase
        )
    }

    @Singleton
    @Provides
    fun provideNewsListViewModelFactory(
        application: Application,
        getNewsListUseCase: GetNewsListUseCase
    ): NewsListViewModelFactory {
        return NewsListViewModelFactory(
            application,
            getNewsListUseCase
        )
    }

    @Singleton
    @Provides
    fun provideDetailNewsViewModelFactory(
        application: Application,
        getDetailNews: GetDetailNews
    ): DetailNewsFactory {
        return DetailNewsFactory(
            application,
            getDetailNews
        )
    }

}
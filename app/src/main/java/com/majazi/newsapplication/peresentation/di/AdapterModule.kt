package com.majazi.newsapplication.peresentation.di

import android.app.Application
import android.content.Context
import com.majazi.newsapplication.BaseApplication
import com.majazi.newsapplication.peresentation.adapter.DetailNewsAdapter
import com.majazi.newsapplication.peresentation.adapter.HomeNewsAdapter
import com.majazi.newsapplication.peresentation.adapter.NewsListAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {
    @Singleton
    @Provides
    fun provideNewsAdapter():HomeNewsAdapter{
        return HomeNewsAdapter()
    }

    @Singleton
    @Provides
    fun provideNewsListAdapter():NewsListAdapter{
        return NewsListAdapter()
    }

    @Singleton
    @Provides
    fun provideDetailNewsAdapter(context: Application):DetailNewsAdapter{
        return DetailNewsAdapter(context)
    }
}
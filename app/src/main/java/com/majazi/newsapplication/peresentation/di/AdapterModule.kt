package com.majazi.newsapplication.peresentation.di

import android.app.Application
import com.majazi.newsapplication.peresentation.adapter.CommentAdapter
import com.majazi.newsapplication.peresentation.adapter.DetailNewsAdapter
import com.majazi.newsapplication.peresentation.adapter.HomeNewsAdapter
import com.majazi.newsapplication.peresentation.adapter.NewsListAdapter
import com.majazi.newsapplication.peresentation.adapter.SavedNewsAdapter
import com.majazi.newsapplication.peresentation.adapter.SearchNewsAdapter
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


    @Singleton
    @Provides
    fun provideSavedNewsAdapter():SavedNewsAdapter{
        return SavedNewsAdapter()
    }


    @Singleton
    @Provides
    fun provideSearchAdapter():SearchNewsAdapter{
        return SearchNewsAdapter()
    }

    @Singleton
    @Provides
    fun provideCommentAdapter():CommentAdapter{
        return CommentAdapter()
    }
}
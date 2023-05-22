package com.majazi.newsapplication.peresentation.di

import android.app.Application
import android.provider.DocumentsContract.Root
import androidx.room.Room
import com.majazi.newsapplication.data.db.NewsDao
import com.majazi.newsapplication.data.db.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Singleton
    @Provides
    fun provideNewsDatabase(app:Application):NewsDatabase{
        return Room.databaseBuilder(app,NewsDatabase::class.java,"news_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideNewsDao(newsDatabase: NewsDatabase):NewsDao{
        return newsDatabase.newsDao()
    }
}
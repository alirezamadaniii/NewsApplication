package com.majazi.newsapplication.peresentation.di

import com.majazi.newsapplication.peresentation.adapter.HomeNewsAdapter
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
}
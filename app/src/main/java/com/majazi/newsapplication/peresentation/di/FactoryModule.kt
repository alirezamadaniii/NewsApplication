package com.majazi.newsapplication.peresentation.di

import android.app.Application
import com.majazi.newsapplication.domien.usecase.GetNewsUseCase
import com.majazi.newsapplication.peresentation.viewmodel.NewsViewModelFactory
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
    ):NewsViewModelFactory{
        return NewsViewModelFactory(
            application,
            getNewsUseCase
        )
    }
}
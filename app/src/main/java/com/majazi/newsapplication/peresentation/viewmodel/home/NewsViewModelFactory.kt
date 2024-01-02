package com.majazi.newsapplication.peresentation.viewmodel.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.majazi.newsapplication.domien.usecase.GetAppIconUseCase
import com.majazi.newsapplication.domien.usecase.GetNewsUseCase
import com.majazi.newsapplication.domien.usecase.GetTrendingNewsUseCase


class NewsViewModelFactory(
    private val app:Application,
    private val getNewsUseCase: GetNewsUseCase,
    private val getTrendingNewsUseCase: GetTrendingNewsUseCase,
    private val getAppIconUseCase: GetAppIconUseCase
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(
            app,
            getNewsUseCase,
            getTrendingNewsUseCase,
            getAppIconUseCase) as T
    }
}
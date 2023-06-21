package com.majazi.newsapplication.peresentation.viewmodel.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.majazi.newsapplication.domien.usecase.GetNewsUseCase
import com.majazi.newsapplication.domien.usecase.GetTrendingNewsUseCase
import com.majazi.newsapplication.domien.usecase.SaveNewsUseCase

class NewsViewModelFactory(
    private val app:Application,
    private val getNewsUseCase: GetNewsUseCase,
    private val getTrendingNewsUseCase: GetTrendingNewsUseCase
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(app, getNewsUseCase,getTrendingNewsUseCase) as T
    }
}
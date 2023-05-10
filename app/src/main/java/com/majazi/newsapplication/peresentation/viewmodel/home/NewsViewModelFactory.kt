package com.majazi.newsapplication.peresentation.viewmodel.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.majazi.newsapplication.domien.usecase.GetNewsUseCase

class NewsViewModelFactory(
    private val app:Application,
    private val getNewsUseCase: GetNewsUseCase
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(app, getNewsUseCase) as T
    }
}
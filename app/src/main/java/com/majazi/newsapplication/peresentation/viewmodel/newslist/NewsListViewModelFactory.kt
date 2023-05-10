package com.majazi.newsapplication.peresentation.viewmodel.newslist

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.majazi.newsapplication.domien.usecase.GetNewsListUseCase

class NewsListViewModelFactory(
     private val app:Application,
     private val getNewsListUseCase: GetNewsListUseCase
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewListViewModel(app, getNewsListUseCase) as T
    }
}
package com.majazi.newsapplication.peresentation.viewmodel.newslist

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.majazi.newsapplication.domien.usecase.GetNewsFromDbUseCase
import com.majazi.newsapplication.domien.usecase.GetNewsListUseCase
import com.majazi.newsapplication.domien.usecase.SaveNewsUseCase

class NewsListViewModelFactory(
     private val app:Application,
     private val getNewsListUseCase: GetNewsListUseCase,
     private val saveNewsUseCase: SaveNewsUseCase,
     private val getNewsFromDbUseCase: GetNewsFromDbUseCase


):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewListViewModel(app, getNewsListUseCase,saveNewsUseCase,getNewsFromDbUseCase) as T
    }
}
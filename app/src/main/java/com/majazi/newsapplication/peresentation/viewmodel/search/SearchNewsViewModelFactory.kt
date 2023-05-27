package com.majazi.newsapplication.peresentation.viewmodel.search

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.majazi.newsapplication.domien.usecase.GetSearchNewsUseCase

class SearchNewsViewModelFactory(
    private val app:Application,
    private val getSearchNewsUseCase: GetSearchNewsUseCase
):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchNewsViewModel(app, getSearchNewsUseCase) as T
    }
}
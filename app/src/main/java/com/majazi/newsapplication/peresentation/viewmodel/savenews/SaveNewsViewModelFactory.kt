package com.majazi.newsapplication.peresentation.viewmodel.savenews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.majazi.newsapplication.domien.usecase.DeleteNewsUseCase
import com.majazi.newsapplication.domien.usecase.GetSavedNewsUseCase

class SaveNewsViewModelFactory(
    private val deleteNewsUseCase: DeleteNewsUseCase,
    private val  getSavedNewsUseCase: GetSavedNewsUseCase
    ):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SaveNewsViewModel(deleteNewsUseCase,getSavedNewsUseCase) as T
    }
}
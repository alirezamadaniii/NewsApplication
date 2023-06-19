package com.majazi.newsapplication.peresentation.viewmodel.savenews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.majazi.newsapplication.domien.usecase.DeleteNewsUseCase

class SaveNewsViewModelFactory(
    private val deleteNewsUseCase: DeleteNewsUseCase
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SaveNewsViewModel(deleteNewsUseCase) as T
    }
}
package com.majazi.newsapplication.peresentation.viewmodel.savenews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.majazi.newsapplication.domien.usecase.DeleteNewsUseCase
import com.majazi.newsapplication.domien.usecase.GetNewsFromDbUseCase

class SaveNewsViewModelFactory(
    private val deleteNewsUseCase: DeleteNewsUseCase,
    private val  getNewsFromDbUseCase: GetNewsFromDbUseCase
    ):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SaveNewsViewModel(deleteNewsUseCase,getNewsFromDbUseCase) as T
    }
}
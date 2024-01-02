package com.majazi.newsapplication.peresentation.viewmodel.savenews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.majazi.newsapplication.data.model.DataSavedList
import com.majazi.newsapplication.domien.usecase.DeleteNewsUseCase
import com.majazi.newsapplication.domien.usecase.GetNewsFromDbUseCase
import kotlinx.coroutines.launch

class SaveNewsViewModel(
    private val deleteNewsUseCase: DeleteNewsUseCase,
    private val getSavedNewsUseCase:GetNewsFromDbUseCase
    ):ViewModel() {

    fun deleteNews(data: DataSavedList) = viewModelScope.launch {
        deleteNewsUseCase.execute(data)
    }

    fun getSavedNews() = liveData {
        getSavedNewsUseCase.execute().collect{
            emit(it)
        }
    }
}
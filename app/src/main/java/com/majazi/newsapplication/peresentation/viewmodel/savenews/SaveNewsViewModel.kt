package com.majazi.newsapplication.peresentation.viewmodel.savenews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.majazi.newsapplication.data.model.newslist.Data
import com.majazi.newsapplication.domien.usecase.DeleteNewsUseCase
import com.majazi.newsapplication.domien.usecase.GetSavedNewsUseCase
import kotlinx.coroutines.launch

class SaveNewsViewModel(
    private val deleteNewsUseCase: DeleteNewsUseCase,
    private val getSavedNewsUseCase: GetSavedNewsUseCase
):ViewModel() {

    fun deleteNews(data: Data) = viewModelScope.launch {
        deleteNewsUseCase.execute(data)
    }

    fun getSavedNews(isSave:Boolean) = liveData {
        getSavedNewsUseCase.execute(isSave).collect{
            emit(it)
        }
    }
}
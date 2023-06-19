package com.majazi.newsapplication.peresentation.viewmodel.savenews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.majazi.newsapplication.data.model.newslist.Data
import com.majazi.newsapplication.domien.usecase.DeleteNewsUseCase
import kotlinx.coroutines.launch

class SaveNewsViewModel(
    private val deleteNewsUseCase: DeleteNewsUseCase
):ViewModel() {

    fun deleteNews(data: Data) = viewModelScope.launch {
        deleteNewsUseCase.execute(data)
    }
}
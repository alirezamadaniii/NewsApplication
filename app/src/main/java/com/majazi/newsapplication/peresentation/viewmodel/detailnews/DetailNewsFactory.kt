package com.majazi.newsapplication.peresentation.viewmodel.detailnews

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.majazi.newsapplication.domien.usecase.GetCommentUseCase
import com.majazi.newsapplication.domien.usecase.GetDetailNewsUseCase
import com.majazi.newsapplication.domien.usecase.SaveNewsUseCase

class DetailNewsFactory(
    private val app:Application,
    private val getDetailNewsUseCase: GetDetailNewsUseCase,
    private val getCommentUseCase: GetCommentUseCase
) :ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailNewsViewModel(app, getDetailNewsUseCase,getCommentUseCase) as T
    }
}
package com.majazi.newsapplication.peresentation.viewmodel.detailnews

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.majazi.newsapplication.domien.usecase.GetCommentUseCase
import com.majazi.newsapplication.domien.usecase.GetDetailNewsUseCase
import com.majazi.newsapplication.domien.usecase.GetUserUseCase
import com.majazi.newsapplication.domien.usecase.SaveNewsUseCase
import com.majazi.newsapplication.domien.usecase.SendCommentUseCase
import com.majazi.newsapplication.domien.usecase.SignInUserUseCase

class DetailNewsFactory(
    private val app:Application,
    private val getDetailNewsUseCase: GetDetailNewsUseCase,
    private val getCommentUseCase: GetCommentUseCase,
    private val signInUserUseCase: SignInUserUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val sendCommentUseCase: SendCommentUseCase
) :ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailNewsViewModel(
            app,
            getDetailNewsUseCase,
            getCommentUseCase,
            signInUserUseCase,
            getUserUseCase,
            sendCommentUseCase) as T
    }
}
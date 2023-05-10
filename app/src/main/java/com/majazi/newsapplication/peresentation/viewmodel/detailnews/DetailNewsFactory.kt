package com.majazi.newsapplication.peresentation.viewmodel.detailnews

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.majazi.newsapplication.domien.usecase.GetDetailNews

class DetailNewsFactory(
    private val app:Application,
    private val getDetailNews: GetDetailNews
) :ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailNewsViewModel(app, getDetailNews) as T
    }
}
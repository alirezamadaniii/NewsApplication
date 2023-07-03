package com.majazi.newsapplication.domien.usecase

import com.majazi.newsapplication.data.model.newslist.Data

import com.majazi.newsapplication.data.utils.ResourceListNews
import com.majazi.newsapplication.domien.repository.NewsRepository

class GetNewsListUseCase(private val repository: NewsRepository) {
    suspend fun execute(catId:String,internet: Boolean): ResourceListNews<Data> {
        return repository.getListNews(catId,internet)
    }
}
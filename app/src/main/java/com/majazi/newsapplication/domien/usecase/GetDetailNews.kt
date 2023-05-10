package com.majazi.newsapplication.domien.usecase

import com.majazi.newsapplication.data.model.detailnews.DetailNews
import com.majazi.newsapplication.data.utils.Resource
import com.majazi.newsapplication.domien.repository.NewsRepository

class GetDetailNews(private val newsRepository: NewsRepository) {
    suspend fun execute(id:String):Resource<DetailNews> {
        return newsRepository.getDetailNews(id)
    }
}
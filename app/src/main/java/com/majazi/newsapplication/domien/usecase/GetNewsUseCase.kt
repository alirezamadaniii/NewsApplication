package com.majazi.newsapplication.domien.usecase

import com.majazi.newsapplication.data.model.homenews.HomeNews
import com.majazi.newsapplication.data.utils.Resource
import com.majazi.newsapplication.domien.repository.NewsRepository

class GetNewsUseCase (private val newsRepository: NewsRepository){
    suspend fun execute():Resource<HomeNews> {
        return newsRepository.getNews()
    }
}
package com.majazi.newsapplication.domien.usecase

import com.majazi.newsapplication.data.model.homenews.ItemNews
import com.majazi.newsapplication.data.utils.ResourceItemNews
import com.majazi.newsapplication.domien.repository.NewsRepository

class GetNewsUseCase (private val newsRepository: NewsRepository){
    suspend fun execute(internet:Boolean): ResourceItemNews<ItemNews> {
        return newsRepository.getNews(internet)
    }
}
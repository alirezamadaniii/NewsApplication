package com.majazi.newsapplication.domien.usecase

import com.majazi.newsapplication.data.model.homenews.ItemNews
import com.majazi.newsapplication.data.utils.Resource2
import com.majazi.newsapplication.domien.repository.NewsRepository

class GetNewsUseCase (private val newsRepository: NewsRepository){
    suspend fun execute():Resource2<ItemNews> {
        return newsRepository.getNews()
    }
}
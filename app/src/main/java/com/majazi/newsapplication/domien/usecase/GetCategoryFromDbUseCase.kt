package com.majazi.newsapplication.domien.usecase

import com.majazi.newsapplication.data.model.homenews.ItemNews
import com.majazi.newsapplication.domien.repository.NewsRepository

class GetCategoryFromDbUseCase(private val newsRepository: NewsRepository) {

//    suspend fun execute():List<ItemNews>{
//        return newsRepository.getCategoryFromDb()
//    }
}
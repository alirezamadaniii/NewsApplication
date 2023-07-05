package com.majazi.newsapplication.domien.usecase

import com.majazi.newsapplication.data.model.homenews.ItemNews
import com.majazi.newsapplication.data.model.newslist.Data
import com.majazi.newsapplication.domien.repository.NewsRepository

class SaveNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(data: Data) = newsRepository.saveNewsToSaved(data)
}
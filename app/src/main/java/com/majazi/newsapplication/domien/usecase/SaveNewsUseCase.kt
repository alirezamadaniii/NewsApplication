package com.majazi.newsapplication.domien.usecase

import com.majazi.newsapplication.data.model.DataSavedList
import com.majazi.newsapplication.domien.repository.NewsRepository

class SaveNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(data: DataSavedList) = newsRepository.saveNewsToSaved(data)
}
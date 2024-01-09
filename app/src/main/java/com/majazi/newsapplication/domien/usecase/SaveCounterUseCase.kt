package com.majazi.newsapplication.domien.usecase

import com.majazi.newsapplication.data.model.Category
import com.majazi.newsapplication.domien.repository.NewsRepository

class SaveCounterUseCase(private val repository: NewsRepository) {
    suspend fun execute(category: Category) = repository.addCounter(category)
}
package com.majazi.newsapplication.domien.usecase

import com.majazi.newsapplication.data.model.newslist.Data
import com.majazi.newsapplication.domien.repository.NewsRepository

class DeleteNewsUseCase(private val repository: NewsRepository) {
    suspend fun execute(data: Data) = repository.deleteNews(data)

}
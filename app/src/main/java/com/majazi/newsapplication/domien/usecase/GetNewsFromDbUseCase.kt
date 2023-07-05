package com.majazi.newsapplication.domien.usecase

import com.majazi.newsapplication.data.model.newslist.Data
import com.majazi.newsapplication.domien.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSavedNewsUseCase(private val repository: NewsRepository) {
    suspend fun execute(isSaved:Boolean):Flow<List<Data>>{
        return repository.getSavedNews(isSaved)
    }
}
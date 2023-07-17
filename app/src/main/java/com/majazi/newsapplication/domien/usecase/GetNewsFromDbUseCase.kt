package com.majazi.newsapplication.domien.usecase

import com.majazi.newsapplication.data.model.DataSavedList
import com.majazi.newsapplication.data.model.newslist.Data
import com.majazi.newsapplication.domien.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNewsFromDbUseCase(private val repository: NewsRepository) {
    suspend fun execute():Flow<List<DataSavedList>>{
        return repository.getSavedNews()
    }
}
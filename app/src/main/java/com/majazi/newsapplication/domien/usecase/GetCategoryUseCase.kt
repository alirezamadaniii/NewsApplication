package com.majazi.newsapplication.domien.usecase

import com.majazi.newsapplication.data.model.Category
import com.majazi.newsapplication.data.utils.Resource
import com.majazi.newsapplication.domien.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetCategoryUseCase(private val repository: NewsRepository) {
     suspend fun execute(categoryId:Int):Flow<Int>{
        return repository.getCounter(categoryId)
    }
}
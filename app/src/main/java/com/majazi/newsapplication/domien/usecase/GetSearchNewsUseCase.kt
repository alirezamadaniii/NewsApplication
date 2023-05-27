package com.majazi.newsapplication.domien.usecase

import com.majazi.newsapplication.data.model.search.Search
import com.majazi.newsapplication.data.utils.Resource
import com.majazi.newsapplication.domien.repository.NewsRepository

class GetSearchNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(search: String):Resource<Search>{
        return newsRepository.getNewsFromSearch(search)
    }
}
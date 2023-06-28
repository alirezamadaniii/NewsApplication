package com.majazi.newsapplication.domien.usecase

import com.majazi.newsapplication.data.model.trendingnews.Post
import com.majazi.newsapplication.data.model.trendingnews.TrendingNews
import com.majazi.newsapplication.data.utils.Resource
import com.majazi.newsapplication.data.utils.ResourceTrending
import com.majazi.newsapplication.domien.repository.NewsRepository

class GetTrendingNewsUseCase(private val repository: NewsRepository) {
    suspend fun execute():ResourceTrending<Post>{
        return repository.getTrendingNews()
    }
}
package com.majazi.newsapplication.domien.usecase

import com.majazi.newsapplication.data.model.trendingnews.Post
import com.majazi.newsapplication.data.utils.ResourceTrending
import com.majazi.newsapplication.domien.repository.NewsRepository

class GetTrendingNewsUseCase(private val repository: NewsRepository) {
    suspend fun execute(internet: Boolean):ResourceTrending<Post>{
        return repository.getTrendingNews(internet)
    }
}
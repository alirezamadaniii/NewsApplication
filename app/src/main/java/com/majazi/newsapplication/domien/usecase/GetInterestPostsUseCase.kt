package com.majazi.newsapplication.domien.usecase

import com.majazi.newsapplication.data.model.interestpost.InterestPost
import com.majazi.newsapplication.data.utils.Resource
import com.majazi.newsapplication.domien.repository.NewsRepository

class GetInterestPostsUseCase(private val repository: NewsRepository) {
    suspend fun execute(userId:String,categoryId: String): Resource<InterestPost> {
        return repository.getInterestPosts(userId,categoryId)
    }

}
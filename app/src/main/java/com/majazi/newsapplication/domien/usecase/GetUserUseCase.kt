package com.majazi.newsapplication.domien.usecase

import com.majazi.newsapplication.data.model.detailnews.comment.SignInUser
import com.majazi.newsapplication.domien.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetUserUseCase(private val repository: NewsRepository) {
     fun execute(): Flow<SignInUser> {
         return repository.getUser()
     }
}
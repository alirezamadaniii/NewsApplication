package com.majazi.newsapplication.domien.usecase

import com.majazi.newsapplication.data.model.detailnews.comment.SignInUser
import com.majazi.newsapplication.domien.repository.NewsRepository

class SignInUserUseCase(private val repository: NewsRepository) {
    suspend fun execute(signInUser: SignInUser){
        repository.signInUser(signInUser)
    }
}
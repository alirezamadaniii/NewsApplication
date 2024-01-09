package com.majazi.newsapplication.domien.usecase

import com.majazi.newsapplication.data.model.getuser.GetUser
import com.majazi.newsapplication.data.utils.Resource
import com.majazi.newsapplication.domien.repository.NewsRepository

class GetUserAuthUseCase(private val repository: NewsRepository) {
    suspend fun execute(name:String,phone:String): Resource<GetUser> {
        return repository.userAuth(name,phone)
    }
}
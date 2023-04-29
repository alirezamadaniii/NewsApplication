package com.majazi.newsapplication.data.repository.news

import com.majazi.newsapplication.data.model.homenews.HomeNews
import com.majazi.newsapplication.data.repository.news.datasource.NewsRemoteDataSource
import com.majazi.newsapplication.data.utils.Resource
import com.majazi.newsapplication.domien.repository.NewsRepository
import retrofit2.Response

class NewsRepositoryImpl(
    private val remoteDataSource: NewsRemoteDataSource
):NewsRepository {
    override suspend fun getNews(): Resource<HomeNews> {
        return responseToResource(remoteDataSource.getNews())
    }

    private fun responseToResource(response: Response<HomeNews>):Resource<HomeNews>{
        if (response.isSuccessful){
            response.body()?.let {result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }
}
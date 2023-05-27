package com.majazi.newsapplication.data.repository.news

import com.majazi.newsapplication.data.model.detailnews.DetailNews
import com.majazi.newsapplication.data.model.homenews.HomeNews
import com.majazi.newsapplication.data.model.homenews.ItemNews
import com.majazi.newsapplication.data.model.newslist.Data
import com.majazi.newsapplication.data.model.newslist.NewsList
import com.majazi.newsapplication.data.model.search.Search
import com.majazi.newsapplication.data.repository.news.datasource.NewsLocalDataSource
import com.majazi.newsapplication.data.repository.news.datasource.NewsRemoteDataSource
import com.majazi.newsapplication.data.utils.Resource
import com.majazi.newsapplication.domien.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepositoryImpl(
    private val remoteDataSource: NewsRemoteDataSource,
    private val localDataSource: NewsLocalDataSource
):NewsRepository {
    override suspend fun getNews(): Resource<HomeNews> {
        return responseToResource(remoteDataSource.getNews())
    }

    override suspend fun getListNews(catId:String): Resource<NewsList> {
        return responseToResourceNewsList(remoteDataSource.getNewsList(catId))
    }

    override suspend fun getDetailNews(id: String): Resource<DetailNews> {
        return responseToResourceDetailNews(remoteDataSource.getDetailNews(id))
    }

    override suspend fun saveNews(data: Data) {
        localDataSource.saveNewsToDB(data)
    }

    override suspend fun getNewsFromDb(): Flow<List<Data>> {
        return localDataSource.getNewsFromDb()
    }

    override suspend fun getNewsFromSearch(search: String): Resource<Search> {
        return responseToResourceSearch(remoteDataSource.getNewsFromSearch(search))
    }


    private fun responseToResource(response: Response<HomeNews>):Resource<HomeNews>{
        if (response.isSuccessful){
            response.body()?.let {result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())

    }


    private fun responseToResourceNewsList(response: Response<NewsList>):Resource<NewsList>{
        if (response.isSuccessful){
            response.body()?.let {result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }


    private fun responseToResourceDetailNews(response: Response<DetailNews>):Resource<DetailNews>{
        if (response.isSuccessful){
            response.body()?.let {result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

    private fun responseToResourceSearch(response: Response<Search>):Resource<Search>{
        if (response.isSuccessful){
            response.body()?.let {result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }
}
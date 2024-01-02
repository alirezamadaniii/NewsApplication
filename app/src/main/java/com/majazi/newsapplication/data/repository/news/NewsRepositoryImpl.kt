package com.majazi.newsapplication.data.repository.news

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.majazi.newsapplication.data.NewsPagingSource
import com.majazi.newsapplication.data.model.DataSavedList
import com.majazi.newsapplication.data.model.detailnews.DetailNews
import com.majazi.newsapplication.data.model.detailnews.comment.Comment
import com.majazi.newsapplication.data.model.detailnews.comment.SignInUser
import com.majazi.newsapplication.data.model.detailnews.comment.sendcomment.SendComment
import com.majazi.newsapplication.data.model.homenews.ItemNews
import com.majazi.newsapplication.data.model.newslist.Data
import com.majazi.newsapplication.data.model.newslist.NewsList
import com.majazi.newsapplication.data.model.search.Search
import com.majazi.newsapplication.data.model.trendingnews.Post
import com.majazi.newsapplication.data.model.trendingnews.TrendingNews
import com.majazi.newsapplication.data.repository.news.datasource.NewsLocalDataSource
import com.majazi.newsapplication.data.repository.news.datasource.NewsRemoteDataSource
import com.majazi.newsapplication.data.utils.Resource
import com.majazi.newsapplication.data.utils.ResourceItemNews
import com.majazi.newsapplication.data.utils.ResourceListNews
import com.majazi.newsapplication.data.utils.ResourceTrending
import com.majazi.newsapplication.domien.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepositoryImpl(
    private val remoteDataSource: NewsRemoteDataSource,
    private val localDataSource: NewsLocalDataSource
):NewsRepository {
    override suspend fun getNews(internet:Boolean): ResourceItemNews<ItemNews> {
            return ResourceItemNews.Success(getCategoryFromDb(internet))
    }

    override suspend fun getListNews(catId:String,internet: Boolean,page:String,number:String): ResourceListNews<Data> {
        return ResourceListNews.Success(getNewsListFromDb(catId,internet,page, number))

    }

    override suspend fun getSavedNews(): Flow<List<DataSavedList>> {
        return localDataSource.getSaveNews()
    }

    override suspend fun getDetailNews(id: String): Resource<DetailNews> {
        return responseToResourceDetailNews(remoteDataSource.getDetailNews(id))
    }

    override suspend fun saveNewsToSaved(data: DataSavedList) {
        localDataSource.saveNewsToSaved(data)
    }


//    override suspend fun saveNews(data: Data) {
//        localDataSource.saveNewsToDB(data)
//    }

//    override suspend fun getNewsFromDb(): Flow<List<Data>> {
//        return localDataSou rce.getNewsFromDb()
//    }

    override suspend fun getNewsFromSearch(search: String): Resource<Search> {
        return responseToResourceSearch(remoteDataSource.getNewsFromSearch(search))
    }


    override suspend fun getComment(id: String): Resource<Comment> {
        return responseToResourceComment(remoteDataSource.getComment(id))
    }

    override suspend fun deleteNews(data: DataSavedList) {
        localDataSource.deleteNews(data)
    }

    override suspend fun getTrendingNews(internet: Boolean): ResourceTrending<Post> {
        return ResourceTrending.Success(getTreadingFromDb(internet))
    }

    override suspend fun signInUser(signInUser: SignInUser) {
        return localDataSource.signInUser(signInUser)
    }

    override fun getUser(): Flow<SignInUser> {
        return localDataSource.getUser()
    }

    override suspend fun sendComment(
        comment: String,
        postId: String,
        name: String,
        phone: String
    ): Resource<SendComment> {
        return responseToResourceSendComment(
            remoteDataSource.sendComment(comment, postId, name, phone))
    }

    override suspend fun getAppIcon(): Resource<TrendingNews> {
        return responseToResourceAppIcon(remoteDataSource.getAppIcon())
    }


    private fun responseToResourceSendComment(response: Response<SendComment>):Resource<SendComment>{
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

    private fun responseToResourceAppIcon(response: Response<TrendingNews>):Resource<TrendingNews>{
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

    private fun responseToResourceComment(response: Response<Comment>):Resource<Comment>{
        if (response.isSuccessful){
            response.body()?.let {result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }


    private suspend fun getCategoryNewsFromApi():List<ItemNews>{
        lateinit var categoryList:List<ItemNews>
        try {
            val response = remoteDataSource.getNews()
            val body = response.body()
            if (body!=null){
                categoryList = body.data
            }
        }catch (e:Exception){
            Log.i("TAG", "getCategoryNewsFromApi: ${e.message}")
        }
        return categoryList
    }


    private suspend fun getCategoryFromDb(internet: Boolean):List<ItemNews>{
        lateinit var categoryList:List<ItemNews>
        try {
           categoryList = localDataSource.getCategoryFromDb()
        }catch (e:Exception){
            Log.i("TAG", "getCategoryNewsFromApi: ${e.message}")
        }
        if (categoryList.size>0){
            return if (internet){
                categoryList =getCategoryNewsFromApi()
                localDataSource.saveCategoryToDb(categoryList)
                categoryList
            }else{
                categoryList
            }

        }else{
            categoryList =getCategoryNewsFromApi()
            localDataSource.saveCategoryToDb(categoryList)

        }
        return categoryList
    }




    private suspend fun getTradingNewsFromApi():List<Post>{
        lateinit var categoryList:List<Post>
        try {
            val response = remoteDataSource.getTrendingNews()
            val body = response.body()
            if (body!=null){
                categoryList = body.data.post
            }
        }catch (e:Exception){
            Log.i("TAG", "getCategoryNewsFromApi: ${e.message}")
        }
        return categoryList
    }


    private suspend fun getTreadingFromDb(internet: Boolean):List<Post>{
        lateinit var categoryList:List<Post>
        try {
            categoryList = localDataSource.getTrendingNews()
        }catch (e:Exception){
            Log.i("TAG", "getCategoryNewsFromApi: ${e.message}")
        }
        if (categoryList.size>0){
            return if (internet){
                categoryList =getTradingNewsFromApi()
                localDataSource.saveTrendingNewsToDb(categoryList)
                categoryList
            }else{
                categoryList
            }
        }else{
            categoryList =getTradingNewsFromApi()
            localDataSource.saveTrendingNewsToDb(categoryList)

        }
        return categoryList
    }


    private suspend fun getNewsListFromApi(catId:String,page:String,number:String):List<Data>{
        lateinit var categoryList:List<Data>
        try {
            val response = remoteDataSource.getNewsList(catId,page, number)
            val body = response.body()
            if (body!=null){
                categoryList = body.data
            }
        }catch (e:Exception){
            Log.i("TAG", "getCategoryNewsFromApi: ${e.message}")
        }
        return categoryList
    }


    private suspend fun getNewsListFromDb(catId:String, internet: Boolean,page:String,number:String):List<Data>{
        lateinit var categoryList:List<Data>
        try {
            categoryList = localDataSource.getNewsFromDb(catId)
        }catch (e:Exception){
            Log.i("TAG", "getCategoryNewsFromApi: ${e.message}")
        }
        if (categoryList.size>0){
            return if (internet){
                categoryList =getNewsListFromApi(catId, page, number)
                localDataSource.saveNewsToDB(categoryList)
                categoryList
            }else{
                categoryList
            }
        }else{
            categoryList =getNewsListFromApi(catId,page,number)
            localDataSource.saveNewsToDB(categoryList)

        }
        return categoryList
    }

}
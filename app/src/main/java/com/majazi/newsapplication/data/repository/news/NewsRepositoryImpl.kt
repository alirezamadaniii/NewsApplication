package com.majazi.newsapplication.data.repository.news

import com.majazi.newsapplication.data.model.Category
import com.majazi.newsapplication.data.model.DataSavedList
import com.majazi.newsapplication.data.model.detailnews.DetailNews
import com.majazi.newsapplication.data.model.detailnews.comment.Comment
import com.majazi.newsapplication.data.model.detailnews.comment.SignInUser
import com.majazi.newsapplication.data.model.detailnews.comment.sendcomment.SendComment
import com.majazi.newsapplication.data.model.getuser.GetUser
import com.majazi.newsapplication.data.model.homenews.ItemNews
import com.majazi.newsapplication.data.model.interestpost.InterestPost
import com.majazi.newsapplication.data.model.newslist.Data
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
            return ResourceItemNews.Success(getCategory(internet))
    }

    override suspend fun getListNews(catId:String,internet: Boolean,page:String,number:String): ResourceListNews<Data> {
        return ResourceListNews.Success(getNewsList(catId,internet,page, number))

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
        return ResourceTrending.Success(getTreading(internet))
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

    override suspend fun userAuth(name: String, phone: String): Resource<GetUser> {
        return responseToResourceUserAuth(remoteDataSource.userAuth(name, phone))
    }

    override suspend fun getInterestPosts(
        userId: String,
        categoryId: String
    ): Resource<InterestPost> {
        return responseToResourceInterestPost(remoteDataSource.interestPosts(userId, categoryId))
    }

    override suspend fun addCounter(category: Category) {
        localDataSource.addCounter(category)
    }

    override suspend fun getCounter(categoryId: Int): Flow<Int> {
        return localDataSource.getCounter(categoryId)
    }

    override suspend fun getAllCounter(): Flow<List<Category>> {
        return localDataSource.getAllCounter()
    }

    private fun responseToResourceInterestPost(response: Response<InterestPost>):Resource<InterestPost>{
        if (response.isSuccessful){
            response.body()?.let {result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())

    }


    private fun responseToResourceUserAuth(response: Response<GetUser>):Resource<GetUser>{
        if (response.isSuccessful){
            response.body()?.let {result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())

    }
    private fun responseToResourceSendComment(response: Response<SendComment>):Resource<SendComment>{
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



    private suspend fun getCategory(internet: Boolean):List<ItemNews>{
       return if (internet){
           val list = remoteDataSource.getNews()
           if (localDataSource.getCategoryFromDb().size>=10){
               localDataSource.deleteNewsList()
           }
           localDataSource.saveCategoryToDb(list.body()?.data!!)
           list.body()?.data!!

       }else{
           localDataSource.getCategoryFromDb()
       }
    }




    private suspend fun getTreading(internet: Boolean):List<Post>{
        return if (internet){
            val list = remoteDataSource.getTrendingNews()
            if (localDataSource.getTrendingNews().size>=5){
                localDataSource.deleteTrendingNews()
            }
            localDataSource.saveTrendingNewsToDb(list.body()?.data!!.post.subList(0,4))
            list.body()?.data!!.post
        }else{
            localDataSource.getTrendingNews()
        }
    }




    private suspend fun getNewsList(catId:String, internet: Boolean,page:String,number:String):List<Data>{
        return if (internet){
            val list = remoteDataSource.getNewsList(catId, page, number)
            if (localDataSource.getNewsFromDb(catId).size>=100){
                localDataSource.deleteNewsToDB()
            }
            localDataSource.saveNewsToDB(list.body()?.data!!.subList(0,10))
            list.body()?.data!!
        }else{
            localDataSource.getNewsFromDb(catId)
        }
    }



}
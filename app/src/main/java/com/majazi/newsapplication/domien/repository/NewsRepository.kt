package com.majazi.newsapplication.domien.repository

import androidx.paging.PagingData
import com.majazi.newsapplication.data.model.DataSavedList
import com.majazi.newsapplication.data.model.detailnews.DetailNews
import com.majazi.newsapplication.data.model.detailnews.comment.Comment
import com.majazi.newsapplication.data.model.detailnews.comment.SignInUser
import com.majazi.newsapplication.data.model.detailnews.comment.sendcomment.SendComment
import com.majazi.newsapplication.data.model.homenews.ItemNews
import com.majazi.newsapplication.data.model.newslist.Data
import com.majazi.newsapplication.data.model.search.Search
import com.majazi.newsapplication.data.model.trendingnews.Post
import com.majazi.newsapplication.data.model.trendingnews.TrendingNews
import com.majazi.newsapplication.data.utils.Resource
import com.majazi.newsapplication.data.utils.ResourceItemNews
import com.majazi.newsapplication.data.utils.ResourceListNews
import com.majazi.newsapplication.data.utils.ResourceTrending
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getNews(internet:Boolean): ResourceItemNews<ItemNews>

    suspend fun getListNews(catId:String,internet: Boolean,page:String,number:String): ResourceListNews<Data>

    suspend fun getSavedNews():Flow<List<DataSavedList>>
    suspend fun getDetailNews(id:String):Resource<DetailNews>

    suspend fun saveNewsToSaved(data: DataSavedList)

//    suspend fun getNewsFromDb():Flow<List<Data>>

    suspend fun getNewsFromSearch(search:String):Resource<Search>

    suspend fun getComment(id: String): Resource<Comment>

    suspend fun deleteNews(data: DataSavedList)

    suspend fun getTrendingNews(internet: Boolean):ResourceTrending<Post>

    suspend fun signInUser(signInUser: SignInUser)

     fun getUser():Flow<SignInUser>

     suspend fun sendComment(
         comment: String,
         postId:String,
         name:String,
         phone:String
     ):Resource<SendComment>


     suspend fun getAppIcon():Resource<TrendingNews>
}
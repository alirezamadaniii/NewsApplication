package com.majazi.newsapplication.data.repository.news.datasource

import com.majazi.newsapplication.data.model.detailnews.DetailNews
import com.majazi.newsapplication.data.model.detailnews.comment.Comment
import com.majazi.newsapplication.data.model.detailnews.comment.sendcomment.SendComment
import com.majazi.newsapplication.data.model.getuser.GetUser
import com.majazi.newsapplication.data.model.homenews.HomeNews
import com.majazi.newsapplication.data.model.interestpost.InterestPost
import com.majazi.newsapplication.data.model.newslist.NewsList
import com.majazi.newsapplication.data.model.search.Search
import com.majazi.newsapplication.data.model.trendingnews.TrendingNews
import retrofit2.Response

interface NewsRemoteDataSource {
    suspend fun getNews():Response<HomeNews>

    suspend fun getNewsList(catId:String,page:String,number:String):Response<NewsList>

    suspend fun getDetailNews(id:String):Response<DetailNews>

    suspend fun getNewsFromSearch(search: String):Response<Search>

    suspend fun getComment(id: String):Response<Comment>

    suspend fun getTrendingNews():Response<TrendingNews>

    suspend fun sendComment(
        comment: String,
        postId:String,
        name:String,
        phone:String
    ):Response<SendComment>

    suspend fun getAppIcon():Response<TrendingNews>

    suspend fun userAuth(name: String,phone: String):Response<GetUser>


    suspend fun interestPosts(userId: String,categoryId: String):Response<InterestPost>
}
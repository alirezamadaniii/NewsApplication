package com.majazi.newsapplication.domien.repository

import com.majazi.newsapplication.data.model.detailnews.DetailNews
import com.majazi.newsapplication.data.model.homenews.HomeNews
import com.majazi.newsapplication.data.model.homenews.ItemNews
import com.majazi.newsapplication.data.model.newslist.Data
import com.majazi.newsapplication.data.model.newslist.NewsList
import com.majazi.newsapplication.data.utils.Resource

interface NewsRepository {

    suspend fun getNews():Resource<HomeNews>

    suspend fun getListNews(catId:String):Resource<NewsList>
    suspend fun getDetailNews(id:String):Resource<DetailNews>

    suspend fun saveNews(data: Data)

}
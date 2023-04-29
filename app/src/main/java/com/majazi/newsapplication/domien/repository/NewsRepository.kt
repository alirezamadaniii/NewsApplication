package com.majazi.newsapplication.domien.repository

import com.majazi.newsapplication.data.model.homenews.HomeNews
import com.majazi.newsapplication.data.utils.Resource

interface NewsRepository {

    suspend fun getNews():Resource<HomeNews>
//    suspend fun updateNews():List<>
}
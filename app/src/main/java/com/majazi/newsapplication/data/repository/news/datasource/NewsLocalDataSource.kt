package com.majazi.newsapplication.data.repository.news.datasource

interface NewsLocalDataSource {
//    suspend fun getNewsFromDB():List<>
//    suspend fun saveNewsToDB():List<>
    suspend fun clearAll()
}
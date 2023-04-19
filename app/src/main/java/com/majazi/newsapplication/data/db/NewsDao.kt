package com.majazi.newsapplication.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NewsDao {

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun saveNews(news:List<>)
//
//    @Query("DELETE FROM ")
//    suspend fun deleteAllNews()
//
//    @Query("SELECT * FROM ")
//    suspend fun getNewsList():List<>
}
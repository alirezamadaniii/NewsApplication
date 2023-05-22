package com.majazi.newsapplication.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.majazi.newsapplication.data.model.homenews.ItemNews
import com.majazi.newsapplication.data.model.newslist.Data

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNews(data:Data)
//
//    @Query("DELETE FROM ")
//    suspend fun deleteAllNews()
//
//    @Query("SELECT * FROM ")
//    suspend fun getNewsList():List<>
}
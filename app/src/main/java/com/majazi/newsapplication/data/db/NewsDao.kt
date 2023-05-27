package com.majazi.newsapplication.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.majazi.newsapplication.data.model.homenews.ItemNews
import com.majazi.newsapplication.data.model.newslist.Data
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNews(data:Data)
//
//    @Query("DELETE FROM ")
//    suspend fun deleteAllNews()
//
    @Query("SELECT * FROM news_list")
     fun getNewsList(): Flow<List<Data>>
}
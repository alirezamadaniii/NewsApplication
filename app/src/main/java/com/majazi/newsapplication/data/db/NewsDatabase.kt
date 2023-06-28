package com.majazi.newsapplication.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.majazi.newsapplication.data.model.homenews.ItemNews
import com.majazi.newsapplication.data.model.newslist.Data
import com.majazi.newsapplication.data.model.trendingnews.Post
import com.majazi.newsapplication.data.model.trendingnews.TNews

@Database(
    entities = [Data::class,ItemNews::class,Post::class],
    version = 3,
    exportSchema = false)
@TypeConverters(Converters::class)
abstract class NewsDatabase :RoomDatabase(){
    abstract fun newsDao():NewsDao
}
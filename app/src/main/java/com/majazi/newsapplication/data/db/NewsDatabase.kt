package com.majazi.newsapplication.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.majazi.newsapplication.data.model.DataSavedList
import com.majazi.newsapplication.data.model.detailnews.comment.SignInUser
import com.majazi.newsapplication.data.model.homenews.ItemNews
import com.majazi.newsapplication.data.model.newslist.Data
import com.majazi.newsapplication.data.model.trendingnews.Post

@Database(
    entities = [Data::class,ItemNews::class,Post::class,SignInUser::class,DataSavedList::class],
    version = 15,
    exportSchema = false)
@TypeConverters(Converters::class)
abstract class NewsDatabase :RoomDatabase(){
    abstract fun newsDao():NewsDao
}
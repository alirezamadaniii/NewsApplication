package com.majazi.newsapplication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.majazi.newsapplication.data.model.newslist.Image

@Entity(
    tableName = "save_news_list"
)
data class DataSavedList (

    val created: String,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val image: Image,
    val title: String,

)
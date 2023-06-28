package com.majazi.newsapplication.data.model.trendingnews

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trending_news")
data class Post(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String
)
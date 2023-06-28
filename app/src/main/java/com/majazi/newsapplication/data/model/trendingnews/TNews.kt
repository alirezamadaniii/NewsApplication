package com.majazi.newsapplication.data.model.trendingnews

import androidx.room.Entity


data class TNews(
    val appIcon: String,
    val post: List<Post>
)
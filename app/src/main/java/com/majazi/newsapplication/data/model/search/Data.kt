package com.majazi.newsapplication.data.model.search

data class Data(
    val category: Category,
    val category_id: Int,
    val commentsCount: Int,
    val content: String,
    val created: String,
    val id: Int,
    val image: Image,
    val image_id: Int,
    val slug: String,
    val title: String,
    val user: User,
    val user_id: Int,
    val video: Any,
    val video_id: Any
)
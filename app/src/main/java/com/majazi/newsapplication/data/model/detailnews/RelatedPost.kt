package com.majazi.newsapplication.data.model.detailnews

data class RelatedPost(
    val category: CategoryX,
    val category_id: Int,
    val commentsCount: Int,
    val created: String,
    val id: Int,
    val image: Image,
    val image_id: Int,
    val post_type: String,
    val slug: String,
    val title: String,
    val user: User,
    val user_id: Int
)
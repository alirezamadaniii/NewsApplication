package com.majazi.newsapplication.data.model.detailnews.comment

data class Data(
    val comment: String,
    val comment_id: Any,
    val date: String,
    val id: Int,
    val post_id: Int,
    val status: Int,
    val user: User,
    val user_id: Int
)
package com.majazi.newsapplication.data.model.detailnews.comment

data class Comment(
    val `data`: List<Data>,
    val message: String,
    val success: Boolean
)
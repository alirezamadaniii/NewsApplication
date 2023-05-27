package com.majazi.newsapplication.data.model.search

data class Search(
    val `data`: List<Data>,
    val message: String,
    val success: Boolean
)
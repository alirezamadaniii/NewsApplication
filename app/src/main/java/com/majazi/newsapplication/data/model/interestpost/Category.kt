package com.majazi.newsapplication.data.model.interestpost


import com.squareup.moshi.Json

data class Category(
    @Json(name = "category_name")
    val categoryName: String,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "is_featured")
    val isFeatured: Int,
    @Json(name = "language")
    val language: String,
    @Json(name = "meta_description")
    val metaDescription: String,
    @Json(name = "meta_keywords")
    val metaKeywords: String,
    @Json(name = "order")
    val order: Int,
    @Json(name = "slug")
    val slug: String,
    @Json(name = "top_news")
    val topNews: Any,
    @Json(name = "updated_at")
    val updatedAt: String
)
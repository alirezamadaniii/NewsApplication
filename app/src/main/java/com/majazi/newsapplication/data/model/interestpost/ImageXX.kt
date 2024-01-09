package com.majazi.newsapplication.data.model.interestpost


import com.squareup.moshi.Json

data class ImageXX(
    @Json(name = "alt")
    val alt: Any,
    @Json(name = "big_image")
    val bigImage: String,
    @Json(name = "big_image_two")
    val bigImageTwo: String,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "disk")
    val disk: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "medium_image")
    val mediumImage: String,
    @Json(name = "medium_image_three")
    val mediumImageThree: String,
    @Json(name = "medium_image_two")
    val mediumImageTwo: String,
    @Json(name = "og_image")
    val ogImage: String,
    @Json(name = "original_image")
    val originalImage: String,
    @Json(name = "small_image")
    val smallImage: String,
    @Json(name = "thumbnail")
    val thumbnail: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "updated_at")
    val updatedAt: String,
    @Json(name = "user_id")
    val userId: Any
)
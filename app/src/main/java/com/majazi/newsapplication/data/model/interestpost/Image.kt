package com.majazi.newsapplication.data.model.interestpost


import com.squareup.moshi.Json

data class Image(
    @Json(name = "image_id")
    val imageId: String
)
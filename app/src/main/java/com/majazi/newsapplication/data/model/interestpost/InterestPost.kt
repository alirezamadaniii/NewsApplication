package com.majazi.newsapplication.data.model.interestpost


import com.squareup.moshi.Json

data class InterestPost(
    @Json(name = "data")
    val `data`: List<Data>,
    @Json(name = "message")
    val message: String,
    @Json(name = "success")
    val success: Boolean
)
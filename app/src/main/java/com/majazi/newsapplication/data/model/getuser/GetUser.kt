package com.majazi.newsapplication.data.model.getuser


import com.squareup.moshi.Json

data class GetUser(
    @Json(name = "data")
    val `data`: List<Any>,
    @Json(name = "message")
    val message: Message,
    @Json(name = "success")
    val success: Boolean
)
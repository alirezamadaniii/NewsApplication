package com.majazi.newsapplication.data.model.getuser


import com.squareup.moshi.Json

data class Message(
    @Json(name = "user_id")
    val userId: Int
)
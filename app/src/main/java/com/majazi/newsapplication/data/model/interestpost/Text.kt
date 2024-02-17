package com.majazi.newsapplication.data.model.interestpost


import com.squareup.moshi.Json

data class Text(
    @Json(name = "text")
    val text: String
)
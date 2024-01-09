package com.majazi.newsapplication.data.model.interestpost


import com.squareup.moshi.Json

data class Contents(
    @Json(name = "1")
    val x1: X1,
    @Json(name = "2")
    val x2: X1
)
package com.example.global.modules.category.model.like


import com.squareup.moshi.Json

data class ResponseLike(
    @Json(name = "message")
    val message: String?,
    @Json(name = "status")
    val status: Boolean?
)
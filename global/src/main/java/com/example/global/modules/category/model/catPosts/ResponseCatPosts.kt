package com.example.global.modules.category.model.catPosts


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseCatPosts(
    @Json(name = "content")
    var content: List<Content?>?,
    @Json(name = "status")
    var status: Boolean?,
    @Json(name = "subCategories")
    var subCategories: List<SubCategory?>?
)
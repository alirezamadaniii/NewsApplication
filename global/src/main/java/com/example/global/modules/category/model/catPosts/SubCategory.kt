package com.example.global.modules.category.model.catPosts


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SubCategory(
    @Json(name = "id")
    var id: Int?,
    @Json(name = "name")
    var name: String?
)
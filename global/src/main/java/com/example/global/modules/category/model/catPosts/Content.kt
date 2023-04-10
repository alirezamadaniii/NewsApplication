package com.example.global.modules.category.model.catPosts


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Content(
    @Json(name = "age_rating")
    var ageRating: Int?,
    @Json(name = "content_gender")
    var contentGender: Int?,
    @Json(name = "id")
    var id: Int?,
    @Json(name = "likes")
    var likes: Int?,
    @Json(name = "pic_banner")
    var picBanner: String?,
    @Json(name = "text")
    var text: String?,
    @Json(name = "title")
    var title: String?,
    @Json(name = "views")
    var views: Int?
)
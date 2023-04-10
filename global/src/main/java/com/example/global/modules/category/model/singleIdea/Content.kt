package com.example.global.modules.category.model.singleIdea


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Content(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "pic_banner")
    val picBanner: String?,
    @Json(name = "text")
    val text: String?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "views")
    val views: Int?
) : Parcelable
package com.example.global.modules.shared


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Post(
    @Json(name = "body")
    var body: String?,
    @Json(name = "id")
    var id: Int?,
    @Json(name = "image_url")
    var imageUrl: String?,
    @Json(name = "like")
    var like: Int?,
    @Json(name = "title")
    var title: String?,
    @Json(name = "view")
    var view: Int?
) : Parcelable
package com.example.global.modules.category.model.allCats


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class CategoryPost(
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
) : Parcelable
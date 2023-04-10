package com.example.global.modules.category.model.allCats


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class SliderObj(
    @Json(name = "id")
    var id: Int?,
    @Json(name = "title")
    var title: String?,
    @Json(name = "text")
    var text: String?,
    @Json(name = "link")
    var link: String?,
    @Json(name = "image")
    var image: String?
) : Parcelable
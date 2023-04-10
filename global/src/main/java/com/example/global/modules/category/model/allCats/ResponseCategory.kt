package com.example.global.modules.category.model.allCats


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class ResponseCategory(
    @Json(name = "content")
    var content: List<CategoryObj>?,
    @Json(name = "status")
    var status: Boolean?,
    @Json(name = "slider")
    var slider: List<SliderObj>?
) : Parcelable
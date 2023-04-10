package com.example.global.modules.setting.model


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class OtherLink(
    @Json(name = "content")
    var content: List<ContentOtherLink>?,
    @Json(name = "status")
    var status: Boolean?
) : Parcelable
package com.example.global.modules.setting.model


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class ContentOtherLink(
    @Json(name = "name")
    var name: String?,
    @Json(name = "value")
    var value: String?
) : Parcelable
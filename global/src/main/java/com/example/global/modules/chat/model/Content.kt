package com.example.global.modules.chat.model


import android.annotation.SuppressLint
import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@SuppressLint("ParcelCreator")
@Parcelize
data class Content(
    @Json(name = "family")
    var family: String?,
    @Json(name = "id")
    var id: String?,
    @Json(name = "name")
    var name: String?,
    @Json(name = "pic")
    var pic: String?
) : Parcelable
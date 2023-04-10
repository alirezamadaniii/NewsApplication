package com.example.global.modules.message.supportMsg


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class ResponseSupportMsg(
    @Json(name = "content")
    var content: List<SupportMsg?>?,
    @Json(name = "status")
    var status: Boolean?
) : Parcelable
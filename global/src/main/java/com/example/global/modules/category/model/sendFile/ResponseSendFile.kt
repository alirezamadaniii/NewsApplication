package com.example.global.modules.category.model.sendFile


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class ResponseSendFile(
    @Json(name = "message")
    val message: String?,
    @Json(name = "status")
    val status: Boolean?
) : Parcelable
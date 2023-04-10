package com.example.global.modules.message.chatMessage


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Message(
    @Json(name = "created_at")
    var createdAt: String?,
    @Json(name = "message")
    var message: String?,
    @Json(name = "content")
    var content: String?,
    @Json(name = "content_type")
    var contentType: String?
) : Parcelable
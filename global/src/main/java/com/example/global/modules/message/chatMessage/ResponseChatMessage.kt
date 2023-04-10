package com.example.global.modules.message.chatMessage


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class ResponseChatMessage(
    @Json(name = "content")
    var content: Content?,
    @Json(name = "status")
    var status: Boolean?
) : Parcelable
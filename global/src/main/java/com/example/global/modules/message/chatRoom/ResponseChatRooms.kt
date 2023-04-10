package com.example.global.modules.message.chatRoom


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class ResponseChatRooms(
    @Json(name = "content")
    var content: List<ChatRoom?>?,
    @Json(name = "status")
    var status: Boolean?
) : Parcelable
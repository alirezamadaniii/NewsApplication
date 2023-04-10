package com.example.global.modules.chat.model.chatMsg


import android.annotation.SuppressLint
import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@SuppressLint("ParcelCreator")
@Parcelize
data class ChatMsg(
    @Json(name = "chat_message")
    var chatMessage: String?,
    @Json(name = "from_app_id")
    var fromAppId: String?,
    @Json(name = "timestamp")
    var timestamp: String?
) : Parcelable
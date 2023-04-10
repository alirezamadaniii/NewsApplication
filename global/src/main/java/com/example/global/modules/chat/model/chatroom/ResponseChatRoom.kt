package com.example.global.modules.chat.model.chatroom


import android.annotation.SuppressLint
import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@SuppressLint("ParcelCreator")
@Parcelize
data class ResponseChatRoom(
    @Json(name = "chat_list")
    var chatList: List<Chat>?
) : Parcelable
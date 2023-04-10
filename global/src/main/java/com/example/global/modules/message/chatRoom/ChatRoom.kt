package com.example.global.modules.message.chatRoom


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class ChatRoom(
    @Json(name = "created_at")
    var createdAt: String?,
    @Json(name = "id")
    var id: Int?,
    @Json(name = "image")
    var image: String?,
    @Json(name = "name")
    var name: String?,
    @Json(name = "status")
    var status: Int?,
    @Json(name = "updated_at")
    var updatedAt: String?
) : Parcelable
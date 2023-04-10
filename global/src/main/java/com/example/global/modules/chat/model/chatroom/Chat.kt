package com.example.global.modules.chat.model.chatroom


import android.annotation.SuppressLint
import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@SuppressLint("ParcelCreator")
@Parcelize
data class Chat(
    @Json(name = "family")
    var family: String?,
    @Json(name = "name")
    var name: String?,
    @Json(name = "user_id")
    var userId: Int?,
    @Json(name = "ostan")
    var city: String?,
    @Json(name = "path")
    var pic: String?,
    @Json(name = "id")
    var id: Int?,
) : Parcelable
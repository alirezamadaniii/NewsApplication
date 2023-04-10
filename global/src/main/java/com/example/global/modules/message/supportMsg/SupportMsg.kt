package com.example.global.modules.message.supportMsg


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class SupportMsg(
    @Json(name = "content")
    var content: String?,
    @Json(name = "content_type")
    var contentType: String?,
    @Json(name = "created_at")
    var createdAt: String?,
    @Json(name = "text")
    var text: String?,
    @Json(name = "user_send")
    var userSend: Int?
) : Parcelable
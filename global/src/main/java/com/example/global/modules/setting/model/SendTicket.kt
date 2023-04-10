package com.example.global.modules.setting.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SendTicket(
    @Json(name = "message")
    var message: String?,
    @Json(name = "status")
    var status: Boolean?
)
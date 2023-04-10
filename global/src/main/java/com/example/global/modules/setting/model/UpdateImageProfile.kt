package com.example.global.modules.setting.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UpdateImageProfile(
    @Json(name = "result")
    var result: String?,
    @Json(name = "status")
    var status: Boolean?
)
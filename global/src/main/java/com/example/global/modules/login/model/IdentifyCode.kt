package com.example.global.modules.login.model


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class IdentifyCode(
    @Json(name = "message")
    var message: String?,
    @Json(name = "status")
    var status: Boolean?
) : Parcelable
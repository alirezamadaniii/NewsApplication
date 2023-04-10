package com.example.global.modules.message


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class ResponseSendSupportMessage(
    @Json(name = "result")
    var result: String?,
    @Json(name = "status")
    var status: Boolean?
) : Parcelable
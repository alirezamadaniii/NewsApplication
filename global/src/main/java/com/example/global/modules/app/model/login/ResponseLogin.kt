package com.example.global.modules.app.model.login


import android.annotation.SuppressLint
import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@SuppressLint("ParcelCreator")
@Parcelize
data class ResponseLogin(
    @Json(name = "result")
    var result: String?,
    @Json(name = "token")
    var token: String?,
    @Json(name = "id")
    var id: String?,
    @Json(name = "mobile")
    var mobile: String?,
) : Parcelable
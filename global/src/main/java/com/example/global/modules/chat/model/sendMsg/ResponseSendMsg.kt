package com.example.global.modules.chat.model.sendMsg


import android.annotation.SuppressLint
import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@SuppressLint("ParcelCreator")
@Parcelize
data class ResponseSendMsg(
    @Json(name = "result")
    var result: String?
) : Parcelable
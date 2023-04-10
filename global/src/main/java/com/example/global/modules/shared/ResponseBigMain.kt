package com.example.global.modules.shared


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class ResponseBigMain(
    @Json(name = "result")
    var result: List<Result?>?
) : Parcelable
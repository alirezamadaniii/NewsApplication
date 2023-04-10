package com.example.global.modules.app.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class FCMResponse(
    val result: String?
) : Parcelable
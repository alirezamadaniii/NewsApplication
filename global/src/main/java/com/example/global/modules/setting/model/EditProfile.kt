package com.example.global.modules.setting.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class EditProfile(
    @Json(name = "status")
    var status: Boolean?,

    @Json(name = "result")
    var result: String?
) : Parcelable
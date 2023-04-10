package com.example.global.modules.app.model.forceupdate


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class ResponseForceUpdate(
    @Json(name = "available")
    var available: String?,
    @Json(name = "force_version")
    var forceVersion: Int?,
    @Json(name = "link")
    var link: String?,
    @Json(name = "status")
    var status: Boolean?,
    @Json(name = "version")
    var version: Int?
) : Parcelable
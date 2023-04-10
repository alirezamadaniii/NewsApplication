package com.example.global.modules.shared


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class ResponseType3(
    @Json(name = "created_at")
    var createdAt: String?,
    @Json(name = "group_code")
    var groupCode: Int?,
    @Json(name = "id")
    var id: Int?,
    @Json(name = "image")
    var image: String?,
    @Json(name = "link")
    var link: String?,
    @Json(name = "updated_at")
    var updatedAt: String?
) : Parcelable
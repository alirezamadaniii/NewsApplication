package com.example.global.modules.category.model.post


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class FilePost(
    @Json(name = "id")
    var id: Int?,
    @Json(name = "path")
    var path: String?,
    @Json(name = "type")
    var type: String?,
    @Json(name = "file_name")
    var fileName: String?
) : Parcelable


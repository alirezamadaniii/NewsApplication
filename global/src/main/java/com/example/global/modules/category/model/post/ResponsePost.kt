package com.example.global.modules.category.model.post


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class ResponsePost(
    @Json(name = "content")
    var post: Post?,
    @Json(name = "status")
    var status: Boolean?,
    @Json(name = "files")
    var files: List<FilePost>?,
    @Json(name = "isLike")
    var isLike: Boolean?,
    @Json(name = "idea")
    var idea: List<IdeaExperience>?,
    @Json(name = "experience")
    var experience: List<IdeaExperience>?,
) : Parcelable
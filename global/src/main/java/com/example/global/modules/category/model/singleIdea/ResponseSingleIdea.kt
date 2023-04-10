package com.example.global.modules.category.model.singleIdea


import android.os.Parcelable
import com.example.global.modules.category.model.post.FilePost
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class ResponseSingleIdea(
    @Json(name = "content")
    val content: Content?,
    @Json(name = "files")
    val files: List<FilePost>?,
    @Json(name = "status")
    val status: Boolean?
) : Parcelable

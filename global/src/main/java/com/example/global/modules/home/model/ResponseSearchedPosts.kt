package com.example.global.modules.home.model


import android.os.Parcelable
import com.example.global.modules.category.model.post.Post
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class ResponseSearchedPosts(
    @Json(name = "content")
    val content: List<Post?>?,
    @Json(name = "status")
    val status: Boolean?
) : Parcelable
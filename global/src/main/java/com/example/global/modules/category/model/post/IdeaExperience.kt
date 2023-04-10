package com.example.global.modules.category.model.post


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class IdeaExperience(
    @Json(name = "category_id")
    val categoryId: String?,
    @Json(name = "created_at")
    val createdAt: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "pic_banner")
    val picBanner: String?,
    @Json(name = "post_id")
    val postId: Int?,
    @Json(name = "status")
    val status: Int?,
    @Json(name = "text")
    val text: String?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "type")
    val type: Int?,
    @Json(name = "updated_at")
    val updatedAt: String?,
    @Json(name = "views")
    val views: Int?
) : Parcelable
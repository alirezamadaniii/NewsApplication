package com.example.global.modules.shared


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class ResponseType2(
    @Json(name = "category_id")
    var categoryId: String?,
    @Json(name = "created_at")
    var createdAt: String?,
    @Json(name = "id")
    var id: Int?,
    @Json(name = "pic_banner")
    var picBanner: String?,
    @Json(name = "post_id")
    var postId: Int?,
    @Json(name = "status")
    var status: Int?,
    @Json(name = "text")
    var text: String?,
    @Json(name = "title")
    var title: String?,
    @Json(name = "type")
    var type: Int?,
    @Json(name = "updated_at")
    var updatedAt: String?,
    @Json(name = "views")
    var views: Int?
) : Parcelable
package com.example.global.modules.shared


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class ResponseType1(
    @Json(name = "age_rating")
    var ageRating: Int?,
    @Json(name = "category_id")
    var categoryId: Int?,
    @Json(name = "content_gender")
    var contentGender: Int?,
    @Json(name = "created_at")
    var createdAt: String?,
    @Json(name = "id")
    var id: Int?,
    @Json(name = "likes")
    var likes: Int?,
    @Json(name = "pic_banner")
    var picBanner: String?,
    @Json(name = "sub_category_id")
    var subCategoryId: Int?,
    @Json(name = "text")
    var text: String?,
    @Json(name = "title")
    var title: String?,
    @Json(name = "updated_at")
    var updatedAt: String?,
    @Json(name = "views")
    var views: Int?
) : Parcelable
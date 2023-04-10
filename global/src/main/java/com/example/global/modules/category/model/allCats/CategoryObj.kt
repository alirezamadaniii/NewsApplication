package com.example.global.modules.category.model.allCats


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class CategoryObj(
    @Json(name = "background_img")
    var backgroundImg: String?,
    @Json(name = "content")
    var content: List<CategoryPost>?,
    @Json(name = "id")
    var id: Int?,
    @Json(name = "logo_img")
    var logoImg: String?,
    @Json(name = "back_color")
    var backColor: String?,
    @Json(name = "name")
    var name: String?,
    @Json(name = "parent_id")
    var parentId: Int?
) : Parcelable
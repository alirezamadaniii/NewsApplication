package com.example.global.modules.shared


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Result(
    @Json(name = "cat_id")
    var catId: Int?,

    @Json(name = "list")
    var list: List<Post?>?,

    @Json(name = "list1")
    var list1: List<ResponseType1?>?,
    @Json(name = "list2")
    var list2: List<ResponseType2?>?,
    @Json(name = "list3")
    var list3: List<ResponseType3?>?,
    @Json(name = "list4")
    var list4: List<ResponseType4?>?,

    @Json(name = "background")
    var background: String?,
    @Json(name = "back_color")
    var backColor: String?,
    @Json(name = "logo")
    var logo: String?,
    @Json(name = "title")
    var title: String?,
    @Json(name = "type")
    var type: Int?

) : Parcelable
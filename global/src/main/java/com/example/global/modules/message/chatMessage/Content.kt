package com.example.global.modules.message.chatMessage


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Content(
    @Json(name = "current_page")
    var currentPage: Int?,
    @Json(name = "data")
    var `data`: List<Message>?,
    @Json(name = "first_page_url")
    var firstPageUrl: String?,
    @Json(name = "from")
    var from: Int?,
    @Json(name = "next_page_url")
    var nextPageUrl: String?,
    @Json(name = "path")
    var path: String?,
    @Json(name = "per_page")
    var perPage: String?,
    @Json(name = "prev_page_url")
    var prevPageUrl: String?,
    @Json(name = "to")
    var to: Int?
) : Parcelable
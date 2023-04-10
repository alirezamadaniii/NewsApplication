package com.example.global.modules.category.model.sendParticipate


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class ResponseParticipation(
    @Json(name = "message")
    val message: String?,
    @Json(name = "status")
    val status: Boolean?,
    @Json(name = "idea_id")
    val ideaId: Int?,
) : Parcelable
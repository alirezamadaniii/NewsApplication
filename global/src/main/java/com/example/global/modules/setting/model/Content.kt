package com.example.global.modules.setting.model


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Content(
    @Json(name = "address")
    var address: String?,
    @Json(name = "birthday")
    var birthday: String?,
    @Json(name = "code_meli")
    var codeMeli: String?,
    @Json(name = "gender")
    var gender: Int?,
    @Json(name = "Identification_code")
    var identificationCode: String?,
    @Json(name = "image")
    var image: String?,
    @Json(name = "level")
    var level: Int?,
    @Json(name = "mahale")
    var mahale: String?,
    @Json(name = "masjed")
    var masjed: String?,
    @Json(name = "mobile")
    var mobile: String?,
    @Json(name = "name")
    var name: String?,
    @Json(name = "ostan_id")
    var ostanId: String?,
    @Json(name = "shahrestan_id")
    var shahrestanId: String?,
    @Json(name = "ostan")
    var ostan: String?,
    @Json(name = "shahrestan")
    var shahrestan: String?
) : Parcelable
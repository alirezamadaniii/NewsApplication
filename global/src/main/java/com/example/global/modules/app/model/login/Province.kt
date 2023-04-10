package com.example.global.modules.app.model.login


import android.annotation.SuppressLint
import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@SuppressLint("ParcelCreator")
@Parcelize
data class Province(
    @Json(name = "id")
    var id: Int?,
    @Json(name = "province")
    var province: String?,
    @Json(name = "register_code")
    var registerCode: Int?
) : Parcelable
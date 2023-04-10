package com.example.global.modules.shared

import com.squareup.moshi.Json

data class City(
    @Json(name = "id")
    var id: Int?,
    @Json(name = "name")
    var name: String?,
    @Json(name = "name")
    var ostan_id: String?
)
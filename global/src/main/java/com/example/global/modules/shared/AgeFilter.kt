package com.example.global.modules.shared

import com.squareup.moshi.Json

data class AgeFilter(
    @Json(name = "id")
    var id: Int?,
    @Json(name = "name")
    var name: Int?,
    @Json(name = "name2")
    var name2: Int?
    )

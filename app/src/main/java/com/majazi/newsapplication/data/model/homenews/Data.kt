package com.majazi.newsapplication.data.model.homenews


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("category_name")
    val categoryName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("slug")
    val slug: String
)
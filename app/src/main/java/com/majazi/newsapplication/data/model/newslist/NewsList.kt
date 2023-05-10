package com.majazi.newsapplication.data.model.newslist


import com.google.gson.annotations.SerializedName

data class NewsList(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)
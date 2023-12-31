package com.majazi.newsapplication.data.model.homenews


import com.google.gson.annotations.SerializedName

data class HomeNews(
    @SerializedName("data")
    val `data`: List<ItemNews>,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)
package com.majazi.newsapplication.data.model.newslist


import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("big_image")
    val bigImage: String,
    @SerializedName("big_image_two")
    val bigImageTwo: String,
    @SerializedName("medium_image")
    val mediumImage: String,
    @SerializedName("medium_image_three")
    val mediumImageThree: String,
    @SerializedName("medium_image_two")
    val mediumImageTwo: String,
    @SerializedName("og_image")
    val ogImage: String,
    @SerializedName("original_image")
    val originalImage: String,
    @SerializedName("small_image")
    val smallImage: String,
    @SerializedName("thumbnail")
    val thumbnail: String
)
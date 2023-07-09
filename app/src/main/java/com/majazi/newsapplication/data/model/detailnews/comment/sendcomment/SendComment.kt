package com.majazi.newsapplication.data.model.detailnews.comment.sendcomment


import com.google.gson.annotations.SerializedName

data class SendComment(
    @SerializedName("data")
    val `data`: List<Any>,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)
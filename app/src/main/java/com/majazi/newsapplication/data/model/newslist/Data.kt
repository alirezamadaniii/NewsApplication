package com.majazi.newsapplication.data.model.newslist


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("category_id")
    val categoryId: Int,
    @SerializedName("commentsCount")
    val commentsCount: Int,
    @SerializedName("created")
    val created: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: Image,
    @SerializedName("image_id")
    val imageId: Int,
    @SerializedName("post_type")
    val postType: String,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("user")
    val user: User,
    @SerializedName("user_id")
    val userId: Int
)
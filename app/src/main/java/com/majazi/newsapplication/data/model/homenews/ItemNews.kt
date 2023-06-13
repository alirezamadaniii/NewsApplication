package com.majazi.newsapplication.data.model.homenews


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "category_list_home_page"
)
data class ItemNews(
    @SerializedName("category_name")
    val categoryName: String,
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("slug")
    val slug: String
)
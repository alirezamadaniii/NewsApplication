package com.majazi.newsapplication.data.model.detailnews.comment

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class SignInUser(
    @PrimaryKey(autoGenerate = false)
    var username: String,
    var email: String,
)
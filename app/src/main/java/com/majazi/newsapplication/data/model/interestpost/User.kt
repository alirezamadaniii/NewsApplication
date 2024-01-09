package com.majazi.newsapplication.data.model.interestpost


import com.squareup.moshi.Json

data class User(
    @Json(name = "about_us")
    val aboutUs: Any,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "deactivate_reason")
    val deactivateReason: Any,
    @Json(name = "dob")
    val dob: String,
    @Json(name = "email")
    val email: String,
    @Json(name = "firebase_auth_id")
    val firebaseAuthId: Any,
    @Json(name = "first_name")
    val firstName: String,
    @Json(name = "gender")
    val gender: Int,
    @Json(name = "id")
    val id: Int,
    @Json(name = "image_id")
    val imageId: Any,
    @Json(name = "is_active")
    val isActive: Int,
    @Json(name = "is_password_set")
    val isPasswordSet: Int,
    @Json(name = "is_subscribe_banned")
    val isSubscribeBanned: Int,
    @Json(name = "is_user_banned")
    val isUserBanned: Int,
    @Json(name = "last_login")
    val lastLogin: String,
    @Json(name = "last_name")
    val lastName: String,
    @Json(name = "newsletter_enable")
    val newsletterEnable: Int,
    @Json(name = "permissions")
    val permissions: Any,
    @Json(name = "phone")
    val phone: String,
    @Json(name = "profile_image")
    val profileImage: Any,
    @Json(name = "social_media")
    val socialMedia: Any,
    @Json(name = "subscribe_banned_reason")
    val subscribeBannedReason: Any,
    @Json(name = "token")
    val token: Any,
    @Json(name = "updated_at")
    val updatedAt: String,
    @Json(name = "user_banned_reason")
    val userBannedReason: Any,
    @Json(name = "valid_time")
    val validTime: Any
)
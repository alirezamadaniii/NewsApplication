package com.example.global.utils

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.global.BuildConfig
import com.example.global.R
import com.example.global.utils.extensions.disableButton
import com.example.global.utils.extensions.enableButton
import com.google.android.material.button.MaterialButton

// --------------- button -----------------
@BindingAdapter(
    "showProgress"
)
fun MaterialButton.setShowProgress(
    showProgress: Boolean?
) {
    when (showProgress) {
        true -> {
            icon = CircularProgressDrawable(context!!).apply {
                setStyle(CircularProgressDrawable.DEFAULT)
                setColorSchemeColors(ContextCompat.getColor(context!!, R.color.black))
                iconGravity = MaterialButton.ICON_GRAVITY_TEXT_START
                start()
            }
            disableButton(
                backgroundColor = R.color.custom_gray,
                textColor = R.color.black
            )
        }
        null -> {
            disableButton(
                backgroundColor = R.color.custom_gray,
                textColor = R.color.black
            )
        }
        else -> {
            enableButton(
                backgroundColor = R.color.primary10,
                textColor = R.color.white
            )
            icon = null
        }
    }
    if (icon != null) {
        icon.callback = object : Drawable.Callback {
            override fun invalidateDrawable(who: Drawable) {
                this@setShowProgress.invalidate()
            }

            override fun scheduleDrawable(who: Drawable, what: Runnable, `when`: Long) {}
            override fun unscheduleDrawable(who: Drawable, what: Runnable) {}
        }
    }
}


// --------------- ImageView -----------------
@BindingAdapter(
    value = [
        "imageFromUrl", "isImageFromUrlPlaceHolder", "imageFromUrlPlaceHolder"
    ],
    requireAll = false
)
fun ImageView.setImageFromUrl(
    url: String?,
    isImageHasPlaceHolder: Boolean?,
    @DrawableRes placeHolder: Int?
) = when {
    isImageHasPlaceHolder == null && placeHolder == null -> Glide
        .with(this.context)
        .load(BuildConfig.BASE_URL_IMAGE + url)
        .centerCrop()
        .placeholder(R.color.custom_gray2)
        .into(this)
    placeHolder == null -> {
        Log.i("", "setImageFromUrl: ${BuildConfig.BASE_URL_IMAGE}$url")
        Glide
            .with(this.context)
            .load(BuildConfig.BASE_URL_IMAGE + url)
            .centerCrop()
            .placeholder(R.color.custom_gray2)
            .into(this)
    }
    else -> Glide
        .with(this.context)
        .load(BuildConfig.BASE_URL_IMAGE + url)
        .centerCrop()
        .placeholder(placeHolder)
        .into(this)
}


fun ImageView.setImageFromUrlForAds(
    url: String?,
    isImageHasPlaceHolder: Boolean?,
    @DrawableRes placeHolder: Int?
) = when {
    isImageHasPlaceHolder == null && placeHolder == null -> Glide
        .with(this.context)
        .load(BuildConfig.BASE_URL_IMAGE + url)
        .fitCenter()
        .placeholder(R.color.custom_gray2)
        .into(this)
    placeHolder == null -> {
        Log.i("", "setImageFromUrl: ${BuildConfig.BASE_URL_IMAGE}$url")
        Glide
            .with(this.context)
            .load(BuildConfig.BASE_URL_IMAGE + url)
            .fitCenter()
            .placeholder(R.color.custom_gray2)
            .into(this)
    }
    else -> Glide
        .with(this.context)
        .load(BuildConfig.BASE_URL_IMAGE + url)
        .fitCenter()
        .placeholder(placeHolder)
        .into(this)
}


// --------------- TextView -----------------
@SuppressLint("SetTextI18n")
@BindingAdapter("addressText")
fun TextView.bindCustomAddressText(address: String?) {
    text = "آدرس: $address"
}
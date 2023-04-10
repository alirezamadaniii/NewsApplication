package com.example.global.utils.extensions

import android.animation.LayoutTransition
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.annotation.SuppressLint
import android.os.CountDownTimer
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar

fun View.snack(msg: String) = Snackbar.make(
    this,
    msg,
    Snackbar.LENGTH_SHORT
).apply {
    ViewCompat.setLayoutDirection(view, ViewCompat.LAYOUT_DIRECTION_RTL)
}.show()

fun View.customDurationSnack(msg: String, duration: Int) = Snackbar.make(
    this,
    msg,
    Snackbar.LENGTH_INDEFINITE
).setDuration(duration).apply {
    ViewCompat.setLayoutDirection(view, ViewCompat.LAYOUT_DIRECTION_RTL)
}.show()

fun MaterialButton.disableButton(
    @ColorRes backgroundColor: Int,
    @ColorRes textColor: Int
) {
    isClickable = false
    isFocusable = false
    isEnabled = false
    setBackgroundColor(ContextCompat.getColor(context, backgroundColor))
    setTextColor(ContextCompat.getColor(context, textColor))
}

fun MaterialButton.enableButton(
    @ColorRes backgroundColor: Int,
    @ColorRes textColor: Int
) {
    isClickable = true
    isFocusable = true
    isEnabled = true
    setBackgroundColor(ContextCompat.getColor(context, backgroundColor))
    setTextColor(ContextCompat.getColor(context, textColor))
}


fun ViewGroup.enableScaleUpLayoutTransition() {
    val scaleUp = ObjectAnimator.ofPropertyValuesHolder(
        this,
        PropertyValuesHolder.ofFloat("scaleX", .5f, 1f),
        PropertyValuesHolder.ofFloat("scaleY", .5f, 1f)
    ).apply {
        interpolator = OvershootInterpolator()
    }
    val fadeOut = ObjectAnimator.ofFloat(
        this,
        "alpha",
        0f
    )
    layoutTransition = LayoutTransition().apply {
        setAnimator(LayoutTransition.APPEARING, scaleUp)
        setAnimator(LayoutTransition.DISAPPEARING, fadeOut)
    }
}

fun TextView.liveDotsLoading(before: String? = null, after: String? = null): CountDownTimer {
    val dots = StringBuilder()
    val countDownTimer = object : CountDownTimer(3000, 1000) {
        @SuppressLint("SetTextI18n")
        override fun onTick(millisUntilFinished: Long) {
            text = if (before == null && after == null) {
                "${dots.append(".")}"
            } else if (after == null) {
                before + "" + "${dots.append(".")}"
            } else if (before == null) {
                "${dots.append(".")}" + "" + after
            } else {
                before + "" + "${dots.append(".")}" + "" + after
            }
            if (dots.count() == 3)
                dots.clear()
        }

        override fun onFinish() {
            dots.clear()
            start()
        }
    }
    return countDownTimer
}
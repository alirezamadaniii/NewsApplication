package com.majazi.newsapplication.data.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import com.majazi.newsapplication.R

fun Context.dialog(layout: Int, view: View, cancelable: Boolean): Dialog {
    val dialog = Dialog(this, R.style.PauseDialog)
    dialog.setContentView(layout)
    dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    dialog.window!!.setLayout(view.width, view.height)
    dialog.setCancelable(cancelable)
    dialog.show()
    return dialog
}
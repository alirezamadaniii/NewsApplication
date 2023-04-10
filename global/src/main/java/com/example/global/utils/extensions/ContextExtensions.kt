package com.example.global.utils.extensions

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.global.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.util.regex.Pattern

fun Context.alert(
    title: String,
    message: String,
    negativeButtonText: String,
    positiveButtonText: String,
    positiveButtonAction: (DialogInterface) -> Unit
): AlertDialog =
    if (negativeButtonText != "") {
        MaterialAlertDialogBuilder(this, R.style.CustomMaterialAlertDialog)
            .setTitle(title)
            .setMessage(message)
            .setNegativeButton(negativeButtonText) { dialog, _ ->
                dialog.cancel()
            }
            .setPositiveButton(positiveButtonText) { dialog, _ ->
                positiveButtonAction(dialog)
            }
            .show()
    } else {
        MaterialAlertDialogBuilder(this, R.style.CustomMaterialAlertDialog)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(positiveButtonText) { dialog, _ ->
                positiveButtonAction(dialog)
            }
            .show()
    }

fun Context.nonCancelableAlert(
    title: String,
    message: String,
    positiveButtonText: String,
    positiveButtonAction: (DialogInterface) -> Unit
): AlertDialog =
    MaterialAlertDialogBuilder(this, R.style.CustomMaterialAlertDialog)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(positiveButtonText) { dialog, _ ->
            positiveButtonAction(dialog)
        }
        .setCancelable(false)
        .show()


fun Context.dialog(layout: Int, view: View, cancelable: Boolean): Dialog {
    val dialog = Dialog(this)
    dialog.setContentView(layout)
    dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    dialog.window!!.setLayout(view.width, view.height)
    dialog.setCancelable(cancelable)
    dialog.show()
    return dialog
}

fun Context.dialog2(layout: Int, cancelable: Boolean): Dialog {
    val dialog = Dialog(this)
    dialog.setContentView(layout)
    dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    dialog.setCancelable(cancelable)
    dialog.show()
    return dialog
}

fun Context.toast(
    msg: String
) = Toast.makeText(
    this,
    msg,
    Toast.LENGTH_LONG
).show()

fun Context.comingSoon() = Toast.makeText(
    this,
    "به زودی ...",
    Toast.LENGTH_LONG
).show()

fun Context.hideKeyboard(activity: Activity, context: Context) {
    val imm: InputMethodManager =
        context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    //Find the currently focused view, so we can grab the correct window token from it.
    var view = activity.currentFocus
    //If no view currently has focus, create a new one, just so we can grab a window token from it
    if (view == null) {
        view = View(activity)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun String.isEmailValid() =
    Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    ).matcher(this).matches()

package com.example.global.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.Uri
import android.provider.MediaStore
import java.io.File

class Tool {
    @SuppressLint("MissingPermission")
    fun checkInternet(contextThis: Context): Boolean {
        val cm = contextThis.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        if (wifi != null && wifi.isConnected
        ) {
            return true
        }
        val net = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        if (net != null && net.isConnected) {
            return true
        }
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }

    fun delete(context: Context, file: File): Boolean {
        val where = MediaStore.MediaColumns.DATA + "=?"
        val selectionArgs = arrayOf<String>(
            file.absolutePath
        )
        val contentResolver = context.contentResolver
        val filesUri: Uri = MediaStore.Files.getContentUri("external")
        contentResolver.delete(filesUri, where, selectionArgs)
        if (file.exists()) {
            contentResolver.delete(filesUri, where, selectionArgs)
        }
        if (file.exists()) {
            file.delete()
            return false
        } else if (!file.exists()) {
            return true
        }
        return false
    }
}
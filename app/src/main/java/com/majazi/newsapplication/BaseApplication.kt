package com.majazi.newsapplication

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.majazi.newsapplication.data.utils.SaveSharedP
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication :Application(){

    override fun onCreate() {
        super.onCreate()
        checkNightMode()
    }
    private fun checkNightMode() {
        val isNight:String? = SaveSharedP.fetch(this,"night_mode")
        if (isNight.isNullOrEmpty()|| isNight == "false"){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }
}
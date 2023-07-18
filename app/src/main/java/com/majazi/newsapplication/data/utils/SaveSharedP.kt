package com.majazi.newsapplication.data.utils

import android.content.Context
import android.content.SharedPreferences

class SaveSharedP {

    companion object{
        private lateinit var sharedPreferences: SharedPreferences
        lateinit var editor: SharedPreferences.Editor



        fun data(context: Context, key: String?, value: String?) {
            sharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE)
            editor = sharedPreferences.edit()
            editor.putString(key, value)
            editor.apply()
        }

        fun fetch(context: Context, key: String?): String? {
            sharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE)
            return sharedPreferences.getString(key, "")
        }
    }



}
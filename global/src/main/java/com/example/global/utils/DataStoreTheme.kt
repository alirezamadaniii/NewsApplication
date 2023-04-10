package com.example.global.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.global.modules.app.model.login.ResponseLogin
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataStoreTheme @Inject constructor() {

    private val Context.appDataStore: DataStore<Preferences> by preferencesDataStore(
        name = "themeData"
    )

    private val THEME = stringPreferencesKey("theme")



    suspend fun writeTheme(context: Context?, theme: String?) =
        context?.appDataStore?.edit {
            it[THEME] = theme ?: "light"
        }



    fun readTheme(context: Context?) = context?.appDataStore?.data
        ?.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        ?.map {
            it[THEME]
        }


}
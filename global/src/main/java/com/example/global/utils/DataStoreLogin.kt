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
class DataStoreLogin @Inject constructor() {

    private val Context.appDataStore: DataStore<Preferences> by preferencesDataStore(
        name = "appData"
    )

    private val TOKEN = stringPreferencesKey("token")
    private val MOBILE = stringPreferencesKey("mobile")
//    private val NAME = stringPreferencesKey("name")
//    private val IMAGE = stringPreferencesKey("image")


    suspend fun writeToProfile(context: Context?, responseLogin: ResponseLogin?) =
        context?.appDataStore?.edit {
            it[TOKEN] = responseLogin?.token ?: ""
            it[MOBILE] = responseLogin?.mobile ?: ""
        }

    suspend fun delete(context: Context?) =
        context?.appDataStore?.edit {
            it.clear()
        }


    suspend fun writeMobileLogin(context: Context?, mobile: String) =
        context?.appDataStore?.edit {
            it[MOBILE] = mobile
        }


    suspend fun writeTokenLogin(context: Context?, token: String) =
        context?.appDataStore?.edit {
            it[TOKEN] = token
        }


//    suspend fun writeNameLogin(context: Context? , name: String, image: String) =
//        context?.appDataStore?.edit {
//            it[NAME] = name ?: ""
//            it[IMAGE] = image
//        }


    fun readFromProfile(context: Context?) = context?.appDataStore?.data
        ?.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        ?.map {
            ResponseLogin(
                token = it[TOKEN],
                mobile = it[MOBILE],
                id = "",
                result = ""

            )
        }


}
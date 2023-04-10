package com.example.global.di

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.global.network.ApiService
import com.example.global.utils.Consts.Companion.BASE_URL
import com.example.global.utils.DataStoreLogin
import com.example.global.utils.DataStoreTheme
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModules {

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )
            .build()
    }


    @Provides
    @Singleton
    fun provideOkHttp(loggingInterceptor: LoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }


    @Provides
    @Singleton
    fun provideLoggingInterceptor(): LoggingInterceptor {
        return LoggingInterceptor.Builder()
            .setLevel(Level.BASIC)
            .log(Platform.INFO)
            .build()
    }


    @Provides
    @Singleton
    fun providePreferencesDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> =
        PreferenceDataStoreFactory.create(
            produceFile = {
                appContext.preferencesDataStoreFile("ProfileData")
            }
        )


    @Provides
    @Singleton
    fun provideApiServiceApp(retrofit: Retrofit): ApiService.App {
        return retrofit.create(ApiService.App::class.java)
    }


    @Provides
    @Singleton
    fun provideApiServiceSetting(retrofit: Retrofit): ApiService.Setting {
        return retrofit.create(ApiService.Setting::class.java)
    }

    @Provides
    @Singleton
    fun provideApiServiceMySalehin(retrofit: Retrofit): ApiService.MySalehin {
        return retrofit.create(ApiService.MySalehin::class.java)
    }

    @Provides
    @Singleton
    fun provideApiServiceHome(retrofit: Retrofit): ApiService.Home {
        return retrofit.create(ApiService.Home::class.java)
    }

    @Provides
    @Singleton
    fun provideApiServiceLogin(retrofit: Retrofit): ApiService.Login {
        return retrofit.create(ApiService.Login::class.java)
    }

    @Provides
    @Singleton
    fun provideApiServiceCategory(retrofit: Retrofit): ApiService.Category {
        return retrofit.create(ApiService.Category::class.java)
    }

    @Provides
    @Singleton
    fun provideApiServiceMessage(retrofit: Retrofit): ApiService.Messages {
        return retrofit.create(ApiService.Messages::class.java)
    }


    @Singleton
    @Provides
    fun getAppDataStore() = DataStoreLogin()

    @Singleton
    @Provides
    fun getThemeDataStore() = DataStoreTheme()


    @Provides
    @Singleton
    fun provideSharedPrefs(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("loginInformation", Context.MODE_PRIVATE)
    }

}

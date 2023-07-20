package com.example.macttestapp.data

import android.app.Application
import androidx.preference.PreferenceManager
import com.example.macttestapp.MactTestApp.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiFactory(
    application: Application,
) {

    private val preferManager = PreferenceManager.getDefaultSharedPreferences(application)
    private val baseUrl = preferManager.getString(BASE_URL, null) ?: ""

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()
    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    //val apiService: ApiService = retrofit.create()
}
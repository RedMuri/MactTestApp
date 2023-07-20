package com.example.macttestapp

import android.app.Application
import com.example.macttestapp.di.DaggerApplicationComponent

class MactTestApp : Application() {

    val component by lazy {
        DaggerApplicationComponent
            .factory()
            .create(this)
    }

    companion object {

        const val BASE_URL = "base_url"
    }
}
package com.example.macttestapp.di

import android.app.Application
import com.example.macttestapp.data.RepositoryImpl
import com.example.macttestapp.data.network.ApiFactory
import com.example.macttestapp.data.network.ApiService
import com.example.macttestapp.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindRepository(impl: RepositoryImpl): Repository

    companion object {

        @ApplicationScope
        @Provides
        fun provideApiService(
            application: Application,
        ): ApiService? {
            return try {
                ApiFactory(application).apiService
            } catch (_: Exception) {
                null
            }
        }
    }
}
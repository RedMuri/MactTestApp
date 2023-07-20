package com.example.macttestapp.di

import android.app.Application
import com.example.macttestapp.MactTestApp
import com.example.macttestapp.ui.fragments.ProductsFragment
import com.example.macttestapp.ui.fragments.QuotesFragment
import com.example.macttestapp.ui.fragments.SettingsFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(quotesFragment: QuotesFragment)
    fun inject(productsFragment: ProductsFragment)
    fun inject(settingsFragment: SettingsFragment)

    @Component.Factory
    interface ApplicationComponentFactory {

        fun create(
            @BindsInstance application: Application,
        ): ApplicationComponent
    }
}
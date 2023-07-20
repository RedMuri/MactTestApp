package com.example.macttestapp.di

import androidx.lifecycle.ViewModel
import com.example.macttestapp.ui.viewmodel.ProductsViewModel
import com.example.macttestapp.ui.viewmodel.QuotesViewModel
import com.example.macttestapp.ui.viewmodel.SettingsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(QuotesViewModel::class)
    fun bindQuotesViewModel(viewModel: QuotesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProductsViewModel::class)
    fun bindProductsViewModel(viewModel: ProductsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    fun bindSettingsViewModel(viewModel: SettingsViewModel): ViewModel
}
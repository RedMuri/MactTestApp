package com.example.macttestapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.macttestapp.domain.usecases.GetProductsUseCase
import com.example.macttestapp.ui.state.ProductsScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductsViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
) : ViewModel() {

    private val _productsScreenState =
        MutableStateFlow<ProductsScreenState>(ProductsScreenState.Loading)
    val productsScreenState = _productsScreenState.asStateFlow()

    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            getProductsUseCase()
                .catch {
                    _productsScreenState.emit(ProductsScreenState.Error(it))
                }.collect {
                    _productsScreenState.emit(ProductsScreenState.Content(it))
                }
        }
    }
}
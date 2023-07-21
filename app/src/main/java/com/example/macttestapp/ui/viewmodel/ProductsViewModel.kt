package com.example.macttestapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.macttestapp.domain.usecases.GetProductsUseCase
import com.example.macttestapp.ui.state.ProductsScreenState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductsViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
) : ViewModel() {

    private val _productsScreenState =
        MutableStateFlow<ProductsScreenState>(ProductsScreenState.Loading)
    val productsScreenState = _productsScreenState.asStateFlow()
    private val _isRefreshing = MutableSharedFlow<Boolean>()
    val isRefreshing = _isRefreshing.asSharedFlow()

    init {
        getProducts()
    }

    fun getProducts(skip: Int = 0, limit: Int = 15) {
        viewModelScope.launch {
            getProductsUseCase(skip, limit)
                .onCompletion {
                    _isRefreshing.emit(false)
                }
                .catch {
                    _productsScreenState.emit(ProductsScreenState.Error(it))
                }.collect {
                    _productsScreenState.emit(ProductsScreenState.Content(it))
                }
        }
    }
}
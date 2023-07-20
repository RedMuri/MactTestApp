package com.example.macttestapp.ui.state

import com.example.macttestapp.domain.model.Product

sealed class ProductsScreenState {
    object Loading : ProductsScreenState()
    data class Content(val products: List<Product>) : ProductsScreenState()
    data class Error(val error: Throwable) : ProductsScreenState()
}
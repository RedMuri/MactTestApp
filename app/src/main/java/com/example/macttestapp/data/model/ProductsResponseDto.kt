package com.example.macttestapp.data.model

import com.google.gson.annotations.SerializedName
import javax.inject.Inject

data class ProductsResponseDto @Inject constructor(
    @SerializedName("products") val products: List<ProductDto>,
)

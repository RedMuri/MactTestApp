package com.example.macttestapp.data.model

import com.google.gson.annotations.SerializedName
import javax.inject.Inject

data class ProductDto @Inject constructor(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("price") val price: Int,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("images") val images: List<String>,
)

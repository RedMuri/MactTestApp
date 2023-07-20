package com.example.macttestapp.domain.model

import javax.inject.Inject

data class Product @Inject constructor(
    val id: Int,
    val title: String,
    val price: Int,
    val thumbnail: String,
    val description: String,
    val images: List<String>
)

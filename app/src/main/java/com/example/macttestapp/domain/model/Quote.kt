package com.example.macttestapp.domain.model

import javax.inject.Inject

data class Quote @Inject constructor(
    val id: Int,
    val author: String,
    val text: String
)

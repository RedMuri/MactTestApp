package com.example.macttestapp.data.model

import com.google.gson.annotations.SerializedName

data class QuoteDto(
    @SerializedName("id") val id: Int,
    @SerializedName("quote") val text: String,
    @SerializedName("author") val author: String,
)

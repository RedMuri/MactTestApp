package com.example.macttestapp.data.model

import com.google.gson.annotations.SerializedName
import javax.inject.Inject

data class QuoteDto @Inject constructor(
    @SerializedName("id") val id: Int,
    @SerializedName("quote") val text: String,
    @SerializedName("author") val author: String,
)

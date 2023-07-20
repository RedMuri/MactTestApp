package com.example.macttestapp.data.model

import com.google.gson.annotations.SerializedName

data class QuotesResponseDto(
    @SerializedName("quotes") val quotes: List<QuoteDto>,
)

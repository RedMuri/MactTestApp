package com.example.macttestapp.data.model

import com.google.gson.annotations.SerializedName
import javax.inject.Inject

data class QuotesResponseDto @Inject constructor(
    @SerializedName("quotes") val quotes: List<QuoteDto>,
)

package com.example.macttestapp.ui.state

import com.example.macttestapp.domain.model.Quote
import javax.inject.Inject

sealed class QuotesScreenState {
    object Loading : QuotesScreenState()
    data class Content @Inject constructor(val quotes: List<Quote>) : QuotesScreenState()
    data class Error @Inject constructor(val error: Throwable) : QuotesScreenState()
}
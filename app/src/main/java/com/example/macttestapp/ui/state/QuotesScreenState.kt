package com.example.macttestapp.ui.state

import com.example.macttestapp.domain.model.Quote

sealed class QuotesScreenState {
    object Loading : QuotesScreenState()
    data class Content(val quotes: List<Quote>) : QuotesScreenState()
    data class Error(val error: Throwable) : QuotesScreenState()
}
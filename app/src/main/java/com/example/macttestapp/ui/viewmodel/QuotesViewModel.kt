package com.example.macttestapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.macttestapp.domain.usecases.GetQuotesUseCase
import com.example.macttestapp.ui.state.QuotesScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class QuotesViewModel(
    private val getQuotesUseCase: GetQuotesUseCase,
) : ViewModel() {

    private val _quotesScreenState = MutableStateFlow<QuotesScreenState>(QuotesScreenState.Loading)
    val quotesScreenState = _quotesScreenState.asStateFlow()

    init {
        getQuotes()
    }

    private fun getQuotes() {
        viewModelScope.launch {
            getQuotesUseCase()
                .catch {
                    _quotesScreenState.emit(QuotesScreenState.Error(it))
                }.collect {
                    _quotesScreenState.emit(QuotesScreenState.Content(it))
                }
        }
    }
}
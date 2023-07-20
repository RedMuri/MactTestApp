package com.example.macttestapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.macttestapp.domain.usecases.GetQuotesUseCase
import com.example.macttestapp.ui.state.QuotesScreenState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class QuotesViewModel @Inject constructor(
    private val getQuotesUseCase: GetQuotesUseCase,
) : ViewModel() {

    private val _quotesScreenState = MutableStateFlow<QuotesScreenState>(QuotesScreenState.Loading)
    val quotesScreenState = _quotesScreenState.asStateFlow()
    private val _isRefreshing = MutableSharedFlow<Boolean>()
    val isRefreshing = _isRefreshing.asSharedFlow()

    init {
        getQuotes()
    }

    fun getQuotes() {
        viewModelScope.launch {
            getQuotesUseCase()
                .onCompletion {
                    _isRefreshing.emit(false)
                }
                .catch {
                    _quotesScreenState.emit(QuotesScreenState.Error(it))
                }.collect {
                    _quotesScreenState.emit(QuotesScreenState.Content(it))
                }
        }
    }
}
package com.example.macttestapp.domain.usecases

import com.example.macttestapp.domain.Repository

class GetQuotesUseCase(private val repository: Repository) {
    operator fun invoke() = repository.getQuotes()
}

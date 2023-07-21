package com.example.macttestapp.domain.usecases

import com.example.macttestapp.domain.Repository
import javax.inject.Inject

class GetQuotesUseCase @Inject constructor(private val repository: Repository) {
    operator fun invoke(skip: Int,limit: Int) = repository.getQuotes(skip, limit)
}

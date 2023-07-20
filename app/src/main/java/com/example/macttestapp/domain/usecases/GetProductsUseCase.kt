package com.example.macttestapp.domain.usecases

import com.example.macttestapp.domain.Repository

class GetProductsUseCase(private val repository: Repository) {
    operator fun invoke() = repository.getProducts()
}

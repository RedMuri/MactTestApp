package com.example.macttestapp.domain.usecases

import com.example.macttestapp.domain.Repository
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(private val repository: Repository) {
    operator fun invoke(skip: Int,limit: Int) = repository.getProducts(skip, limit)
}

package com.example.macttestapp.domain.usecases

import com.example.macttestapp.domain.Repository

class GetServerStatusUseCase(private val repository: Repository) {
    operator fun invoke() = repository.getServerStatus()
}

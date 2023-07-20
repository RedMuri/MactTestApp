package com.example.macttestapp.domain.usecases

import com.example.macttestapp.domain.Repository
import javax.inject.Inject

class GetServerStatusUseCase @Inject constructor(private val repository: Repository) {
    operator fun invoke() = repository.getServerStatus()
}

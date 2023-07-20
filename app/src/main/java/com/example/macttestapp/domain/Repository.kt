package com.example.macttestapp.domain

import com.example.macttestapp.domain.model.Product
import com.example.macttestapp.domain.model.Quote
import com.example.macttestapp.domain.model.ServerStatus
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getQuotes(): Flow<List<Quote>>
    fun getProducts(): Flow<List<Product>>
    fun getServerStatus(): Flow<ServerStatus>
}
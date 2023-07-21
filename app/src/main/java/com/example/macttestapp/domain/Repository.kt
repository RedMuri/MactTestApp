package com.example.macttestapp.domain

import com.example.macttestapp.domain.model.Product
import com.example.macttestapp.domain.model.Quote
import com.example.macttestapp.domain.model.ServerStatus
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getQuotes(skip: Int,limit: Int): Flow<List<Quote>>
    fun getProducts(skip: Int,limit: Int): Flow<List<Product>>
    fun getServerStatus(): Flow<ServerStatus>
}
package com.example.macttestapp.data

import com.example.macttestapp.data.network.ApiService
import com.example.macttestapp.domain.model.Product
import com.example.macttestapp.domain.model.Quote
import com.example.macttestapp.domain.model.ServerStatus
import com.example.macttestapp.domain.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService?,
    private val mapper: Mapper,
) : Repository {

    override fun getQuotes(skip: Int,limit: Int): Flow<List<Quote>> = flow {
        val response = apiService!!.getQuotes(skip, limit)
        val quotes = response.quotes.map { mapper.mapQuoteDtoToEntity(it) }
        emit(quotes)
    }

    override fun getProducts(skip: Int,limit: Int): Flow<List<Product>> = flow {
        val response = apiService!!.getProducts(skip,limit)
        val products = response.products.map { mapper.mapProductDtoToEntity(it) }
        emit(products)
    }

    override fun getServerStatus(): Flow<ServerStatus> = flow {
        val response = apiService!!.getServerStatus()
        emit(mapper.mapServerStatusDtoToEntity(response))
    }
}
package com.example.macttestapp.data.network

import com.example.macttestapp.data.model.ProductsResponseDto
import com.example.macttestapp.data.model.QuotesResponseDto
import com.example.macttestapp.data.model.ServerStatusDto
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("quotes")
    suspend fun getQuotes(
        @Query(QUERY_PARAM_SKIP) skip: Int,
        @Query(QUERY_PARAM_LIMIT) limit: Int,
    ): QuotesResponseDto

    @GET("products")
    suspend fun getProducts(
        @Query(QUERY_PARAM_SKIP) skip: Int,
        @Query(QUERY_PARAM_LIMIT) limit: Int,
    ): ProductsResponseDto

    @GET("http/200")
    suspend fun getServerStatus(): ServerStatusDto

    companion object {

        private const val QUERY_PARAM_SKIP = "skip"
        private const val QUERY_PARAM_LIMIT = "limit"
    }
}
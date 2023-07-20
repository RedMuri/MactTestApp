package com.example.macttestapp.data.network

import com.example.macttestapp.data.model.ProductsResponseDto
import com.example.macttestapp.data.model.QuotesResponseDto
import com.example.macttestapp.data.model.ServerStatusDto
import retrofit2.http.GET


interface ApiService {

    @GET("quotes")
    suspend fun getQuotes(): QuotesResponseDto

    @GET("products")
    suspend fun getProducts(): ProductsResponseDto

    @GET("http/200")
    suspend fun getServerStatus(): ServerStatusDto
}
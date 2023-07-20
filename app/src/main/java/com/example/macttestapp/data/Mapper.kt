package com.example.macttestapp.data

import com.example.macttestapp.data.model.ProductDto
import com.example.macttestapp.data.model.QuoteDto
import com.example.macttestapp.data.model.ServerStatusDto
import com.example.macttestapp.domain.model.Product
import com.example.macttestapp.domain.model.Quote
import com.example.macttestapp.domain.model.ServerStatus
import javax.inject.Inject

class Mapper @Inject constructor(){

    fun mapQuoteDtoToEntity(quoteDto: QuoteDto) = Quote(
        id = quoteDto.id,
        author = quoteDto.author,
        text = quoteDto.text
    )

    fun mapProductDtoToEntity(productDto: ProductDto) = Product(
        id = productDto.id,
        title = productDto.title,
        price = productDto.price,
        description = productDto.description,
        thumbnail = productDto.thumbnail,
        images = productDto.images
    )

    fun mapServerStatusDtoToEntity(serverStatusDto: ServerStatusDto) = ServerStatus(
        code = serverStatusDto.code
    )
}
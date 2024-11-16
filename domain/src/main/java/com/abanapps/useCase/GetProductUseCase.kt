package com.abanapps.useCase

import com.abanapps.repository.ProductRepository

class GetProductUseCase(private val repository: ProductRepository) {

    suspend fun execute() = repository.getProducts()

}
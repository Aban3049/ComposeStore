package com.abanapps.useCase

import com.abanapps.models.Product
import com.abanapps.repository.ProductRepository
import com.plcoding.cryptotracker.core.domain.util.NetworkError
import com.plcoding.cryptotracker.core.domain.util.Result

class GetProductUseCase(private val productRepository: ProductRepository) {
    suspend operator fun invoke(): Result<List<Product>, NetworkError> {
        return productRepository.getProducts()
    }
}

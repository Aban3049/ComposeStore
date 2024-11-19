package com.abanapps.useCase

import com.abanapps.models.Product
import com.abanapps.repository.ProductRepository
import com.plcoding.cryptotracker.core.domain.util.NetworkError
import com.plcoding.cryptotracker.core.domain.util.Result

class GetPopularProductUseCase(private val repository: ProductRepository) {

    suspend operator fun invoke(): Result<List<Product>, NetworkError> {
        return repository.getPopularProducts()
    }

}
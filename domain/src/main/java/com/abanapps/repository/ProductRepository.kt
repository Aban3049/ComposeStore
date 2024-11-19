package com.abanapps.repository

import com.abanapps.models.Product
import com.plcoding.cryptotracker.core.domain.util.NetworkError
import com.plcoding.cryptotracker.core.domain.util.Result

interface ProductRepository {
    suspend fun getFeaturedProducts(): Result<List<Product>, NetworkError>
    suspend fun getPopularProducts(): Result<List<Product>, NetworkError>
}
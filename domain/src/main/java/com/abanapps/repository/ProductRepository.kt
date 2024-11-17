package com.abanapps.repository

import com.abanapps.models.Product
import com.plcoding.cryptotracker.core.domain.util.NetworkError
import com.plcoding.cryptotracker.core.domain.util.Result
import java.sql.Wrapper

interface ProductRepository {
    suspend fun getProducts(): Result<List<Product>, NetworkError>
}
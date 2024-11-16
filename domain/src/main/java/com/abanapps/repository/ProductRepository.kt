package com.abanapps.repository

import com.abanapps.models.Product
import com.abanapps.network.ResultWrapper
import java.sql.Wrapper

interface ProductRepository {

    suspend fun getProducts(): ResultWrapper<List<Product>>
}
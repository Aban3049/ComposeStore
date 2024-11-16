package com.abanapps.repository

import com.abanapps.models.Product
import com.abanapps.network.NetworkService
import com.abanapps.network.ResultWrapper

class ProductRepositoryImp(private val networkService: NetworkService):ProductRepository {

    override suspend fun getProducts(): ResultWrapper<List<Product>> {
        return networkService.getProducts()
    }

}
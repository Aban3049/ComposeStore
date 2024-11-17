package com.abanapps

import com.abanapps.model.ProductDto
import com.abanapps.model.toProduct
import com.abanapps.models.Product
import com.abanapps.network.constructUrl
import com.abanapps.network.safeCall
import com.abanapps.repository.ProductRepository
import com.plcoding.cryptotracker.core.domain.util.NetworkError
import com.plcoding.cryptotracker.core.domain.util.Result
import com.plcoding.cryptotracker.core.domain.util.map
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class NetworkService(private val httpClient: HttpClient) : ProductRepository {

    override suspend fun getProducts(): Result<List<Product>, NetworkError> {
        return safeCall<List<ProductDto>> {
            httpClient.get(
                urlString = constructUrl("/products")
            )
        }.map { response ->
            response.map { it.toProduct() }
        }
    }
}

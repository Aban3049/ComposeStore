package com.abanapps.model

import com.abanapps.models.Product
import kotlinx.serialization.Serializable



@Serializable
data class ProductDto(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: RatingDto
)

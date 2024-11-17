package com.abanapps.model

import com.abanapps.models.Product
import com.abanapps.models.Rating
import kotlinx.serialization.Serializable

@Serializable
data class RatingDto(
    val rate: Double,
    val count: Int
)

fun ProductDto.toProduct(): Product {
    return Product(
        id = id,
        title = title,
        price = price,
        description = description,
        category = category,
        image = image,
        rating = rating.toRating()
    )
}

fun RatingDto.toRating(): Rating {
    return Rating(
        rate = rate,
        count = count
    )
}
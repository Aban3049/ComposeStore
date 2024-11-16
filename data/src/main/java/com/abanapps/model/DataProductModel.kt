package com.abanapps.model

import com.abanapps.models.Product
import kotlinx.serialization.Serializable

@Serializable
data class DataProductModel(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
){

    fun toProduct() = Product(
        id = id,
        title = title,
        price =  price,
        category = category,
        description = description,
        image = image
    )

}

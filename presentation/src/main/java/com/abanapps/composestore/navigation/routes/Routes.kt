package com.abanapps.composestore.navigation.routes

import kotlinx.serialization.Serializable

sealed class Routes {

    @Serializable
    data object HomeScreen : Routes()

    @Serializable
    data object CartScreen : Routes()

    @Serializable
    data object ProfileScreen : Routes()

    @Serializable
    data object SearchScreen : Routes()

    @Serializable
    data class ProductDetailScreen(
        val imageUrl: String,
        val itemTitle: String,
        val itemDescription: String,
        val itemPrice: Double,
        val rating: Double,
        val count: Int
    )

}
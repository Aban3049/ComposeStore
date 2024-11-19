package com.abanapps.composestore.navigation.routes

import kotlinx.serialization.Serializable

sealed class Routes {

    @Serializable
    data object HomeScreen:Routes()

    @Serializable
    data object CartScreen:Routes()

    @Serializable
    data object ProfileScreen:Routes()

    @Serializable
    data object SearchScreen:Routes()

}
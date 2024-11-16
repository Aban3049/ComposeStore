package com.abanapps.composestore.navigation.routes

import kotlinx.serialization.Serializable

sealed class Routes {
    @Serializable
    data object HomeScreen:Routes()
}
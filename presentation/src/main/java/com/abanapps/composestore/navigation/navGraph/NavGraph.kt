package com.abanapps.composestore.navigation.navGraph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.abanapps.composestore.navigation.routes.Routes
import com.abanapps.composestore.screens.detail.ProductDetailScreen
import com.abanapps.composestore.screens.home.HomeScreen

@Composable
fun NavGraph(modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.HomeScreen,
        modifier = modifier
    ) {

        composable<Routes.HomeScreen> {
            HomeScreen(navController)
        }

        composable<Routes.ProductDetailScreen> {

            val imageUrl: Routes.ProductDetailScreen = it.toRoute()
            val itemTitle: Routes.ProductDetailScreen = it.toRoute()
            val description: Routes.ProductDetailScreen = it.toRoute()
            val itemPrice: Routes.ProductDetailScreen = it.toRoute()
            val rating: Routes.ProductDetailScreen = it.toRoute()
            val count: Routes.ProductDetailScreen = it.toRoute()

            ProductDetailScreen(
                imageUrl = imageUrl.imageUrl,
                itemPrice = itemPrice.itemPrice,
                itemTitle = itemTitle.itemTitle,
                description = description.itemDescription,
                rating = rating.rating,
                count = count.count,
                navHostController = navController
            )

        }

    }

}
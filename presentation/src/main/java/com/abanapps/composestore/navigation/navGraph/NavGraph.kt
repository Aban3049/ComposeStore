package com.abanapps.composestore.navigation.navGraph

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.abanapps.composestore.navigation.routes.Routes
import com.abanapps.composestore.screens.home.HomeContent
import com.abanapps.composestore.screens.home.HomeScreen

@Composable
fun NavGraph(modifier: Modifier=Modifier){

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.HomeScreen,
        modifier = modifier
    ) {

        composable<Routes.HomeScreen> {
            HomeScreen(navController)
        }

    }

}
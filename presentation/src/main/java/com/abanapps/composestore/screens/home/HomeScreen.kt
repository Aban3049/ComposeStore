package com.abanapps.composestore.screens.home

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.abanapps.composestore.navigation.routes.Routes
import com.abanapps.composestore.screens.cart.AddToCartScreen
import com.abanapps.composestore.screens.profile.ProfileScreen
import com.abanapps.composestore.screens.search.SearchScreen
import com.abanapps.composestore.utils.Utils.bottomNavBarItems

@Composable
fun HomeScreen(rootNavHostController: NavHostController = rememberNavController()) {

    val bottomNavController = rememberNavController()

    Scaffold(


        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .background(Color(0xFFF8F7F7)),
                verticalAlignment = Alignment.CenterVertically
            ) {

                var selectedItem by remember {
                    mutableIntStateOf(0)
                }

                bottomNavBarItems.forEachIndexed { index, bottomNavBarItem ->


                    NavigationBarItem(selected = selectedItem == index, onClick = {
                        selectedItem = index
                        bottomNavController.navigate(bottomNavBarItem.route!!)
                    }, icon = {
                        Icon(
                            painter = painterResource(bottomNavBarItem.selectedIcon),
                            contentDescription = null,
                            tint = if (selectedItem == index) Color(0xFF6055D8) else Color.Unspecified
                        )
                    })
                }


            }
        }


    ) {

        NavHost(
            navController = bottomNavController,
            startDestination = Routes.HomeScreen,
            modifier = Modifier.padding(it)
        ) {

            composable<Routes.HomeScreen>(enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(durationMillis = 300)
                )
            }) {
                HomeContent(navHostController = rootNavHostController)
            }

            composable<Routes.SearchScreen>(enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(durationMillis = 300)
                )
            }) {
                SearchScreen()
            }

            composable<Routes.CartScreen>(enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(durationMillis = 300)
                )
            }) {
                AddToCartScreen()
            }

            composable<Routes.ProfileScreen>(enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(durationMillis = 300)
                )
            }) {
                ProfileScreen()
            }


        }

    }

}



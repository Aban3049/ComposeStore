package com.abanapps.composestore.utils

import android.graphics.drawable.Icon
import com.abanapps.composestore.R
import com.abanapps.composestore.navigation.routes.Routes

object Utils {

    val bottomNavBarItems = listOf(
        BottomNavBarItem(
            selectedIcon = R.drawable.home,
            unSelectedIcon = R.drawable.home,
            route = Routes.HomeScreen
        ),
        BottomNavBarItem(
            selectedIcon = R.drawable.search,
            unSelectedIcon = R.drawable.search,
            route = Routes.SearchScreen
        ),
        BottomNavBarItem(
            selectedIcon = R.drawable.cart,
            unSelectedIcon = R.drawable.cart,
            route = Routes.CartScreen
        ),
        BottomNavBarItem(
            selectedIcon = R.drawable.profile,
            unSelectedIcon = R.drawable.profile,
            route = Routes.ProfileScreen
        ),
    )

    data class BottomNavBarItem(
        val selectedIcon: Int,
        val unSelectedIcon: Int,
        val route: Routes? = null
    )

}
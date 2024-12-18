package com.abanapps.composestore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.abanapps.composestore.navigation.navGraph.NavGraph
import com.abanapps.composestore.navigation.routes.Routes
import com.abanapps.composestore.screens.home.HomeContent
import com.abanapps.composestore.ui.theme.ComposeStoreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeStoreTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                   NavGraph(modifier = Modifier.padding(innerPadding))

                }
            }
        }
    }
}

package com.abanapps.composestore.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.abanapps.composestore.viewModel.HomeScreenUiEvents
import com.abanapps.composestore.viewModel.HomeViewModel
import com.abanapps.models.Product
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(navHostController: NavHostController, viewModel: HomeViewModel = koinViewModel()) {

    val uiStates = viewModel.uiStates.collectAsState()

    when (uiStates.value) {
        is HomeScreenUiEvents.Error -> {

            val error = (uiStates.value as HomeScreenUiEvents.Error).message

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = error, fontSize = 24.sp)
            }

        }

        HomeScreenUiEvents.Loading -> {

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        HomeScreenUiEvents.NavigateToProductDetail -> {}
        is HomeScreenUiEvents.Success -> {

            val data = (uiStates.value as HomeScreenUiEvents.Success).data

            LazyColumn {

                items(data) {
                    ProductItem(product = it)
                }
            }

        }
    }

}

@Composable
fun ProductItem(product: Product) {

    Card(elevation = CardDefaults.cardElevation(4.dp), modifier = Modifier.padding(8.dp)) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(text = product.title, style = MaterialTheme.typography.titleMedium)
                Text(text = "${product.price}", style = MaterialTheme.typography.bodyMedium)
            }

        }

    }

}
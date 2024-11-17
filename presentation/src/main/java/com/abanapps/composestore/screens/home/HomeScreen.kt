package com.abanapps.composestore.screens.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.abanapps.composestore.R
import com.abanapps.composestore.viewModel.HomeViewModel
import com.abanapps.models.Product
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(navHostController: NavHostController, viewModel: HomeViewModel = koinViewModel()) {

    val uiStates = viewModel.uiStates.collectAsState()

    when (uiStates.value) {
        is HomeViewModel.HomeScreenUiEvents.Error -> {

            val error = (uiStates.value as HomeViewModel.HomeScreenUiEvents.Error).message

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = error, fontSize = 24.sp)
            }

        }

        HomeViewModel.HomeScreenUiEvents.Loading -> {

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        HomeViewModel.HomeScreenUiEvents.NavigateToProductDetail -> {}

        is HomeViewModel.HomeScreenUiEvents.Success -> {

            val data = (uiStates.value as HomeViewModel.HomeScreenUiEvents.Success).products

            Column(modifier = Modifier.fillMaxSize()) {
                FeatureRow(product = data)
            }


        }
    }

}

@Composable
fun FeatureRow(product: List<Product>) {
    LazyRow {


        items(product) {


            Card(
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .width(130.dp)
                    .height(125.dp)
                    .padding(horizontal = 8.dp),
                colors = CardDefaults.cardColors(Color(0xFFF8F7F7))
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {


                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg")
                            .build(),
                        contentScale = ContentScale.FillBounds,
                        contentDescription = it.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp),
                       placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
                        error = painterResource(R.drawable.ic_launcher_background),
                        onError = { errorResult ->
                            Log.e("AsyncImageError", "Error loading image: ${errorResult.result}")
                        },
                        onLoading = {
                            Log.d("AsyncImage", "Image is loading...")
                        },
                        onSuccess = {
                            Log.d("AsyncImage", "Image loaded successfully")
                        }

                    )

                    Text(
                        it.title,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.padding(start = 8.dp),
                        color = Color.Black,
                    )

                    Text(
                        "$ ${it.price}",
                        color = Color.Blue,
                        modifier = Modifier.padding(start = 8.dp),
                        style = MaterialTheme.typography.labelMedium
                    )


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

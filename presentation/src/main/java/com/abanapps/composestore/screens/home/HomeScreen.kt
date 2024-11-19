package com.abanapps.composestore.screens.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.abanapps.composestore.R
import com.abanapps.composestore.ui.theme.Blue
import com.abanapps.composestore.viewModel.HomeViewModel
import com.abanapps.models.Product
import com.valentinilk.shimmer.shimmer
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(navHostController: NavHostController, viewModel: HomeViewModel = koinViewModel()) {

    val uiStates = viewModel.uiStates.collectAsState()
    val popularFeaturedProducts = viewModel.uiStatePopularProduct.collectAsState()


    LazyColumn(modifier = Modifier.fillMaxSize()) {

        item {
            HeaderRow()
            Spacer(modifier = Modifier.height(6.dp))
        }

        item {
            SearchBar(value = "", onValueChange = {})
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Text(
                "Featured",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        item {
            when (uiStates.value) {

                is HomeViewModel.FeatureProductsUiEvents.Error -> {

                    val error =
                        (uiStates.value as HomeViewModel.FeatureProductsUiEvents.Error).message

                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = error, fontSize = 24.sp)
                    }

                }

                HomeViewModel.FeatureProductsUiEvents.Loading -> {
                    Box(
                        modifier = Modifier
                            .shimmer()
                            .fillMaxWidth()
                            .horizontalScroll(
                                rememberScrollState()
                            )
                    ) {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            repeat(5) {
                                Card(
                                    shape = RoundedCornerShape(12.dp),
                                    modifier = Modifier
                                        .width(140.dp)
                                        .height(130.dp)
                                        .padding(horizontal = 8.dp)
                                ) {
                                    Column(modifier = Modifier.fillMaxWidth()) {


                                        Box(modifier = Modifier.size(110.dp)) {

                                        }

                                        Spacer(modifier = Modifier.height(8.dp))

                                        val text = "".take(10)

                                        Text(
                                            text,
                                            style = MaterialTheme.typography.bodyMedium,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.padding(start = 8.dp),
                                            color = Color.Black,
                                        )

                                        Text(
                                            "$ ",
                                            color = Color.Blue,
                                            modifier = Modifier.padding(start = 8.dp),
                                            style = MaterialTheme.typography.labelMedium
                                        )


                                    }
                                }
                            }
                        }
                    }
                }

                HomeViewModel.FeatureProductsUiEvents.NavigateToProductDetail -> {}

                is HomeViewModel.FeatureProductsUiEvents.Success -> {
                    val data =
                        (uiStates.value as HomeViewModel.FeatureProductsUiEvents.Success).products
                    FeatureRow(product = data)
                }
            }

        }

        item {
            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    "See All",
                    color = Blue,
                    modifier = Modifier.align(Alignment.End),
                    style = MaterialTheme.typography.labelLarge
                )

                Text(
                    "Most Popular",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
        }

        item {
            when (popularFeaturedProducts.value) {
                is HomeViewModel.PopularProductsUiEvent.Error -> {}
                HomeViewModel.PopularProductsUiEvent.Loading -> {

                    Box(
                        modifier = Modifier
                            .shimmer()
                            .fillMaxWidth()
                            .horizontalScroll(
                                rememberScrollState()
                            )
                    ) {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            repeat(5) {
                                Card(
                                    shape = RoundedCornerShape(12.dp),
                                    modifier = Modifier
                                        .width(140.dp)
                                        .height(130.dp)
                                        .padding(horizontal = 8.dp)
                                ) {
                                    Column(modifier = Modifier.fillMaxWidth()) {


                                        Box(modifier = Modifier.size(110.dp)) {

                                        }

                                        Spacer(modifier = Modifier.height(8.dp))

                                        val text = "".take(10)

                                        Text(
                                            text,
                                            style = MaterialTheme.typography.bodyMedium,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.padding(start = 8.dp),
                                            color = Color.Black,
                                        )

                                        Text(
                                            "$ ",
                                            color = Color.Blue,
                                            modifier = Modifier.padding(start = 8.dp),
                                            style = MaterialTheme.typography.labelMedium
                                        )


                                    }
                                }
                            }
                        }
                    }

                }

                HomeViewModel.PopularProductsUiEvent.NavigateToProductDetail -> {}
                is HomeViewModel.PopularProductsUiEvent.Success -> {

                    val popularProducts =
                        (popularFeaturedProducts.value as HomeViewModel.PopularProductsUiEvent.Success).product

                    PopularProducts(popularProducts)

                }
            }

        }


    }


}

@Composable
fun HeaderRow() {

    Box(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 16.dp)
            .fillMaxWidth()
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Image(
                painter = painterResource(id = R.drawable.image),
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text("Hello", style = MaterialTheme.typography.bodySmall)
                Text(
                    "M Aban",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Image(
            painter = painterResource(R.drawable.ic_notification),
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .align(Alignment.CenterEnd)
                .clip(CircleShape)
                .background(color = Color.Gray.copy(alpha = 0.3f))
                .padding(8.dp),
            contentScale = ContentScale.Inside
        )

    }

}

@Composable
fun SearchBar(value: String, onValueChange: (String) -> Unit) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(CircleShape),
        placeholder = {
            Text("Search here", style = MaterialTheme.typography.bodyMedium)
        },
        leadingIcon = {
            Image(
                painter = painterResource(R.drawable.ic_search),
                modifier = Modifier.size(24.dp),
                contentDescription = "Search_bar",
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            focusedContainerColor = Color.LightGray.copy(0.3f),
            unfocusedContainerColor = Color.LightGray.copy(0.3f)

        )
    )
}

@Composable
fun FeatureRow(product: List<Product>) {
    LazyRow(modifier = Modifier.padding(horizontal = 8.dp)) {

        items(product) {


            Card(
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .width(140.dp)
                    .height(150.dp)
                    .padding(horizontal = 8.dp),
                colors = CardDefaults.cardColors(Color(0xFFF8F7F7))
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {


                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(it.image)
                            .build(),
                        contentScale = ContentScale.FillBounds,
                        contentDescription = it.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
//                        placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
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

                    Spacer(modifier = Modifier.height(8.dp))

                    val text = it.title.take(10)

                    Text(
                        text,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
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
fun PopularProducts(product: List<Product>) {

    LazyRow(modifier = Modifier.padding(horizontal = 8.dp)) {
        items(product) {

            Card(
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .width(120.dp)
                    .height(160.dp)
                    .padding(horizontal = 8.dp),
                colors = CardDefaults.cardColors(Color(0xFFF8F7F7))
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {


                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(it.image)
                            .build(),
                        contentScale = ContentScale.FillBounds,
                        contentDescription = it.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(110.dp),
//                        placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
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

                    Spacer(modifier = Modifier.height(8.dp))

                    val text = it.title.take(10)

                    Text(
                        text,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
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

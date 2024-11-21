package com.abanapps.composestore.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.abanapps.composestore.R

@Composable
fun ProductDetailScreen(
    imageUrl: String = "",
    itemTitle: String = "",
    description: String = "",
    itemPrice: Double = 0.0,
    rating: Double = 0.0,
    count: Int = 1,
    navHostController: NavHostController
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        )
        {

            AsyncImage(
                model = imageUrl,
                placeholder = painterResource(R.drawable.image),
                contentDescription = null,
                modifier = Modifier
                    .matchParentSize()
                    .clip(RoundedCornerShape(bottomEnd = 12.dp, bottomStart = 12.dp)),
                contentScale = ContentScale.FillBounds
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 15.dp)
                    .align(Alignment.TopStart),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                IconButton(
                    onClick = {
                        navHostController.popBackStack()
                    },
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.LightGray)
                        .alpha(0.5f)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = Color.Black
                    )
                }

                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.LightGray)
                        .alpha(0.5f)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = null,
                        tint = Color.Black
                    )
                }

            }

        }


        ConstraintLayout(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp)
        ) {

            val (title, price, ratingIcon, ratingText, reviewText, descriptionTitleText, descriptionText) = createRefs()

            val titleText = itemTitle.take(10)

            Text(
                titleText,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                fontSize = 22.sp,
                modifier = Modifier.constrainAs(title) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top, 8.dp)
                }
            )

            Text(
                "$ $itemPrice",
                color = Color.Blue,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                modifier = Modifier.constrainAs(price) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top, margin = 8.dp)
                }
            )

            Icon(
                imageVector = Icons.Outlined.Star,
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(ratingIcon) {
                        top.linkTo(title.bottom, margin = 12.dp)
                        start.linkTo(parent.start)
                    }
                    .size(40.dp)
            )

            Text(
                "$rating",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.constrainAs(ratingText) {
                    start.linkTo(ratingIcon.end, margin = 8.dp)
                    top.linkTo(title.bottom, margin = 12.dp)
                    bottom.linkTo(ratingIcon.bottom)
                })

            Text(
                "($count Review)",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.constrainAs(reviewText) {
                    top.linkTo(ratingText.top)
                    start.linkTo(ratingText.end, 8.dp)
                    bottom.linkTo(ratingText.bottom)
                })

            Text(
                "Description",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 20.sp,
                modifier = Modifier.constrainAs(descriptionTitleText) {
                    top.linkTo(ratingIcon.bottom, margin = 18.dp)
                    start.linkTo(parent.start)

                },
                fontWeight = FontWeight.SemiBold
            )

            Text(description, modifier = Modifier.constrainAs(descriptionText) {
                top.linkTo(descriptionTitleText.bottom, margin = 16.dp)
                start.linkTo(parent.start)
            })


        }


    }


}
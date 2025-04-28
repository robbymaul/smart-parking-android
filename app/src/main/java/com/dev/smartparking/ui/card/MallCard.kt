package com.dev.smartparking.ui.card

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dev.smartparking.R
import com.dev.smartparking.ui.component.ButtonComponent
import com.dev.smartparking.ui.component.FavoriteComponent
import com.dev.smartparking.ui.component.RatingComponent
import com.dev.smartparking.ui.theme.SmartParkingTheme

@Composable
fun MallCard(
    modifier: Modifier = Modifier,
    imageUrl: String,
    descriptionImage: String = "",
    rating: String,
    mallName: String,
    isLike: Boolean,
    onClickButton: ()-> Unit
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 6.dp, // Tambahkan shadow
        modifier = modifier
            .width(347.dp)
            .height(153.dp)
            .padding(8.dp) // Tambahkan padding agar tidak terlalu mepet dengan elemen lain
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(8.dp)), // Pastikan gambar terclip mengikuti Card
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = descriptionImage,
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize(),
                error = painterResource(R.drawable.smart_parking_logo1),
                onSuccess = { Log.d("ImageLoad", "Image loaded successfully") },
                onError = { Log.e("ImageLoad", "Error loading image: ${it.result.throwable}") }
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = mallName,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(
                            shadow = Shadow(blurRadius = 0.5f)
                        ),
                        color = Color.White
                    )
                    FavoriteComponent(
                        modifier = Modifier.size(32.dp),
                        onClick = {},
                        isLike = isLike
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    ButtonComponent(
                        modifier = Modifier
                            .width(144.dp)
                            .height(32.13.dp),
                        onClick = onClickButton,
                        cornerRadius = 24.dp,
                        text = R.string.txt_button_book_now,
                        textColor = MaterialTheme.colorScheme.background,
                    )
                    RatingComponent(
                        rating = rating
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun MallCardPreview() {
    SmartParkingTheme (dynamicColor = false) {
        MallCard(
            imageUrl = "https://res.cloudinary.com/dxdtxld4f/image/upload/v1738768682/skripsi/image_mall1_ixzb6u.jpg",
            rating = "4.5",
            mallName = "Margonda City Mall",
            isLike = true,
            onClickButton = {}
        )
    }
}
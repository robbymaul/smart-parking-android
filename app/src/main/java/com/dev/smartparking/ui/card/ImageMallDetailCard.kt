package com.dev.smartparking.ui.card

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
fun ImageMallDetailCard(
    modifier: Modifier = Modifier,
    imageUrl: String,
    imageDescription: String = ""
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 6.dp, 
        modifier = modifier
            .width(350.dp)
            .height(240.dp)
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(8.dp)), // Pastikan gambar terclip mengikuti Card
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = imageDescription,
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize(),
                error = painterResource(R.drawable.smart_parking_logo1),
                onSuccess = { Log.d("ImageLoad", "Image loaded successfully") },
                onError = { Log.e("ImageLoad", "Error loading image: ${it.result.throwable}") }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ImageMallDetailCardPreview() {
    SmartParkingTheme {
        ImageMallDetailCard(
            imageUrl = "https://res.cloudinary.com/dxdtxld4f/image/upload/v1738768682/skripsi/image_mall1_ixzb6u.jpg",
        )
    }
}
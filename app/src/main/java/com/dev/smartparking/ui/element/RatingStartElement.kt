package com.dev.smartparking.ui.element

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.smartparking.ui.theme.SmartParkingTheme

@Composable
fun RatingStartElement(
    modifier: Modifier = Modifier,
    rating: String,
) {
    Row (
        modifier = modifier.padding(8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector =  Icons.Default.Star,
            contentDescription = "start",
            tint = Color.Yellow
        )
        Text(
            text = rating,
            style = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.SansSerif
            )
        )
    }
}

@Preview
@Composable
private fun RatingStartElementPreview() {
    SmartParkingTheme {
        RatingStartElement(
            rating = "4.5"
        )
    }
}
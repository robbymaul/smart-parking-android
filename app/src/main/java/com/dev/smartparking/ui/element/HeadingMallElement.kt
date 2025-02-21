package com.dev.smartparking.ui.element

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.smartparking.ui.theme.SmartParkingTheme

@Composable
fun HeadingMallElement(modifier: Modifier = Modifier) {
    Column (
        modifier = modifier.padding(8.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
    ) {
        Row (
            modifier = Modifier.padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "Margonda City Mall"
            )
            Row {
                Icon(
                    imageVector = Icons.Default.Star,
                    tint = Color.Yellow,
                    contentDescription = ""
                )
                Text(
                    text = "4.5"
                )
            }
        }
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Icon(
                    imageVector = Icons.Outlined.LocationOn,
                    tint = Color.Black,
                    contentDescription = ""
                )
                Text(
                    text = "Jalan Margonda Raya Depok"
                )
            }
            Icon(
                imageVector = Icons.Outlined.FavoriteBorder,
                tint = Color.Black,
                contentDescription = ""
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HeadingMallElementPreview() {
    SmartParkingTheme {
        HeadingMallElement()
    }
}
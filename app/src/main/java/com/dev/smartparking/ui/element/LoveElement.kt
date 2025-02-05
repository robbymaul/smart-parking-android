package com.dev.smartparking.ui.element

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.smartparking.ui.theme.SmartParkingTheme

@Composable
fun LoveElement(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    tint: Color,
) {
    Icon(
        modifier = modifier,
        imageVector = imageVector,
        contentDescription = "love",
        tint = tint
    )
}

@Preview
@Composable
private fun LoveElementPreview() {
    SmartParkingTheme {
        LoveElement(
            imageVector = Icons.Default.FavoriteBorder,
            tint = Color.White
        )
    }
}
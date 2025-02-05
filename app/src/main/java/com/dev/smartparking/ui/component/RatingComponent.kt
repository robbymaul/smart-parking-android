package com.dev.smartparking.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.smartparking.ui.element.RatingStartElement
import com.dev.smartparking.ui.theme.SmartParkingTheme

@Composable
fun RatingComponent(
    modifier: Modifier = Modifier,
    rating: String,
) {
    Box(
        modifier = modifier.shadow(
            elevation = 8.dp,
            shape = RoundedCornerShape(16.dp),
            ambientColor = MaterialTheme.colorScheme.background
        ).clip(RoundedCornerShape(16.dp))
    ) {
        Surface (
            color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.5f),
        ) {
            RatingStartElement(
                rating = rating
            )
        }
    }
}

@Preview
@Composable
private fun RatingComponentPreview() {
    SmartParkingTheme (dynamicColor = false) {
        RatingComponent(rating = "4.5")
    }
}
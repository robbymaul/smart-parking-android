package com.dev.smartparking.ui.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.smartparking.ui.element.LoveElement
import com.dev.smartparking.ui.theme.SmartParkingTheme

@Composable
fun FavoriteComponent(
    modifier: Modifier = Modifier,
    isLike: Boolean,
    onClick: ()-> Unit
) {
   Button(
       modifier = modifier,
       contentPadding = PaddingValues(8.dp),
       shape = CircleShape,
       colors = ButtonDefaults.buttonColors(Color.White),
       onClick = onClick
   ) {
        LoveElement(
            modifier = Modifier.size(100.dp),
            imageVector = Icons.Default.Favorite,
            tint = if (isLike) Color.Yellow else Color.Gray
        )
   }
}

@Preview
@Composable
private fun FavoriteComponentPreview() {
    SmartParkingTheme {
        FavoriteComponent(
            modifier = Modifier.size(32.dp),
            isLike = false,
            onClick = {}
        )
    }
}
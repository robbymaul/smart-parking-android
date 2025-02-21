package com.dev.smartparking.ui.element

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Directions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.smartparking.ui.theme.SmartParkingTheme

@Composable
fun DirectionElement(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    text: String
) {
    Row (
       modifier =  modifier.padding(8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = ""
        )
        Text(
            text = text
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DirectionElementPreview() {
    SmartParkingTheme {
        DirectionElement(
            icon = Icons.Default.Directions,
            text = "1.2km away"
        )
    }
}
package com.dev.smartparking.ui.element

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.smartparking.ui.theme.SmartParkingTheme

@Composable
fun StatusTicketElement(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(
                color = Color.Yellow,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(
                vertical = 4.dp,
                horizontal = 8.dp,
            )

    ) {
        Text(
            text = "On Going",
            style = TextStyle(
                color = Color.White
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun StatusTicketPreview() {
    SmartParkingTheme {
        StatusTicketElement()
    }
}
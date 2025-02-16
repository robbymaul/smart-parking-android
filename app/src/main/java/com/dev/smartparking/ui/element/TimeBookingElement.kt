package com.dev.smartparking.ui.element

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.dev.smartparking.ui.theme.SmartParkingTheme
import org.w3c.dom.Text

@Composable
fun TimeBookingElement(
    modifier: Modifier = Modifier,
    startTime: String,
    endTime: String,
) {
    Box(
        modifier = modifier,
    ) {
        Text(
            text = "$startTime - $endTime",
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        )
    }
}

@Preview
@Composable
private fun TimeBookingElementPreview() {
    SmartParkingTheme {
        TimeBookingElement(
            startTime = "10:00",
            endTime = "12:00",
        )
    }
}
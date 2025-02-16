package com.dev.smartparking.ui.element

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.smartparking.ui.theme.SmartParkingTheme

@Composable
fun HoursBookingElement(
    modifier: Modifier = Modifier,
    text: String
) {
    Box(
        modifier = modifier
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(8.dp),
                clip = false
            )
            .background(
                color = Color.Gray.copy(alpha = 0.5f),
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 2.dp,
                color = Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
            val stroke = Stroke(
                width = 2f,
                pathEffect = pathEffect
            )
            drawRoundRect(
                color = Color.Blue,
                size = size,
                cornerRadius = CornerRadius(8.dp.toPx(), 8.dp.toPx()),
                style = stroke
            )
        }
        Text(
            text = text,
            style = TextStyle(
                color = Color.Blue,
                fontWeight = FontWeight.Bold,
                background = Color.Transparent
            ),
            modifier = Modifier.align(Alignment.Center)
                .padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HoursBookingElementPreview() {
    SmartParkingTheme {
        HoursBookingElement(
            text = "2 Hours"
        )
    }
}
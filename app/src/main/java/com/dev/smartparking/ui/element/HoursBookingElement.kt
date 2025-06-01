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
import androidx.compose.ui.unit.sp
import com.dev.smartparking.data.utils.getHourDifference
import com.dev.smartparking.ui.theme.SmartParkingTheme

@Composable
fun HoursBookingElement(
    modifier: Modifier = Modifier,
    startTime: String,
    endTime: String,
) {
    val hours = getHourDifference(startTime, endTime)

    Box(
        modifier = modifier
            .padding(4.dp)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(10.dp),
                clip = false
            )
            .background(
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            )
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(8.dp) // Padding agar teks tidak mepet ke tepi
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
            val stroke = Stroke(
                width = 2f,
                pathEffect = pathEffect
            )
            drawRoundRect(
                color = Color(0xFF1976D2), // Biru Material
                size = size,
                cornerRadius = CornerRadius(10.dp.toPx(), 10.dp.toPx()),
                style = stroke
            )
        }

        Text(
            text = "$hours Jam",
            style = TextStyle(
                color = Color(0xFF1976D2),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            ),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun HoursBookingElementPreview() {
    SmartParkingTheme {
        HoursBookingElement(
            startTime = "",
            endTime = ""
        )
    }
}
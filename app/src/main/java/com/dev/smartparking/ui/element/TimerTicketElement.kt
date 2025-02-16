package com.dev.smartparking.ui.element

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.outlined.Timelapse
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.dev.smartparking.ui.theme.SmartParkingTheme
import kotlinx.coroutines.delay

@Composable
fun TimerTicketElement(modifier: Modifier = Modifier) {

    var timeInMinutes by remember { mutableStateOf(70) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(60000)
            timeInMinutes++
        }
    }

    val formattedTime = formatTime(timeInMinutes)

    Box(
        modifier = modifier
            .background(
                Color.Red,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(
                vertical = 4.dp,
                horizontal = 8.dp,
            )
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.AccessTime,
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
            Text(
                text = formattedTime,
                style = TextStyle(
                    color = Color.White
                ),
            )
        }
    }
}

fun formatTime(timeInMinutes: Int): String {
    val hours = timeInMinutes / 60
    val remainingMinutes = timeInMinutes % 60
    return String.format("%02d:%02d", hours, remainingMinutes)
}

@Preview(showBackground = true)
@Composable
private fun TimerTicketElementPreview() {
    SmartParkingTheme {
        TimerTicketElement()
    }
}
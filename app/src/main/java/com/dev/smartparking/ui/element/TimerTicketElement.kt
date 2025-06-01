package com.dev.smartparking.ui.element

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev.smartparking.ui.theme.SmartParkingTheme
import kotlinx.coroutines.delay
import java.time.Duration
import java.time.OffsetDateTime

@Composable
fun TimerTicketElement(
    modifier: Modifier = Modifier,
    entry: String,
    exit: String,
    onStatusChanged: (String) -> Unit
) {
    var remainingSeconds by remember { mutableStateOf(0L) }
    var status by remember { mutableStateOf(true) }

    // Hitung waktu tersisa saat komponen pertama kali muncul
    LaunchedEffect(exit) {
        val exitTime = OffsetDateTime.parse(exit)
        val now = OffsetDateTime.now()
        val duration = Duration.between(now, exitTime)
        remainingSeconds = duration.seconds.coerceAtLeast(0)

        while (remainingSeconds > 0) {
            delay(1000)
            remainingSeconds--
        }

        status = false
        onStatusChanged("expired")
    }

    val formattedTime = formatCountdown(remainingSeconds)

    // Warna background berdasarkan status
    val backgroundColor = when {
        !status -> Color.DarkGray
        remainingSeconds <= 300 -> Color(0xFFD32F2F) // merah terang
        else -> Color(0xFF455A64) // abu kebiruan
    }

    val textColor = Color.White

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .padding(horizontal = 10.dp, vertical = 6.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                imageVector = Icons.Default.AccessTime,
                contentDescription = "Time Remaining",
                tint = textColor,
                modifier = Modifier.size(16.dp)
            )
            Text(
                text = if (status) formattedTime else "Expired",
                style = TextStyle(
                    color = textColor,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
    }
}


// Fungsi untuk format detik ke HH:mm:ss
fun formatCountdown(seconds: Long): String {
    val hours = seconds / 3600
    val minutes = (seconds % 3600) / 60
    val secs = seconds % 60
    return String.format("%02d:%02d:%02d", hours, minutes, secs)
}

@Preview(showBackground = true)
@Composable
private fun TimerTicketElementPreview() {
    SmartParkingTheme {
        TimerTicketElement(entry = "", exit = "") {}
    }
}
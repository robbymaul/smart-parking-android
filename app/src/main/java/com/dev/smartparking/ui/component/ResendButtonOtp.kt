package com.dev.smartparking.ui.component

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay

@Composable
fun ResendOtpButton(
    modifier: Modifier = Modifier,
    resendAction: () -> Unit
) {
    var secondsRemaining by remember { mutableStateOf(0) }
    val isRunning = secondsRemaining > 0

    // Timer
    LaunchedEffect(key1 = isRunning) {
        if (isRunning) {
            while (secondsRemaining > 0) {
                delay(1000L)
                secondsRemaining -= 1
            }
        }
    }

    val buttonText = if (isRunning) "Kirim ulang dalam ${secondsRemaining}s" else "Kirim Ulang OTP"

    Button(
        onClick = {
            resendAction()
            secondsRemaining = 120 // 2 menit
        },
        enabled = !isRunning,
        modifier = modifier
    ) {
        Text(buttonText)
    }
}

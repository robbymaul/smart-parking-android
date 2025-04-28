package com.dev.smartparking.ui.element

import android.app.Dialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.dev.smartparking.ui.theme.SmartParkingTheme
import androidx.compose.material3.MaterialTheme as MaterialTheme1

@Composable
fun LoadingDialog(
    isLoading: Boolean,
    progressIndicatorColor: Color = MaterialTheme1.colorScheme.primary,
    progressIndicatorSize: Dp = 48.dp,
) {
    if (isLoading) {
        AlertDialog(
            onDismissRequest = { /* Disable dismiss */ },
            confirmButton = {},
            text = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent) // Transparent background untuk Column
                        .padding(vertical = 16.dp)
                ) {
                    CircularProgressIndicator(
                        color = progressIndicatorColor,
                        strokeWidth = 4.dp,
                        modifier = Modifier.size(progressIndicatorSize)
                    )
                }
            },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            ),
            shape = RoundedCornerShape(12.dp),
            containerColor = Color.Transparent, // Transparent background untuk dialog
            tonalElevation = 0.dp // Hilangkan elevation shadow
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DialogContentPreview() {
    SmartParkingTheme {
        LoadingDialog(isLoading = true)
    }
}
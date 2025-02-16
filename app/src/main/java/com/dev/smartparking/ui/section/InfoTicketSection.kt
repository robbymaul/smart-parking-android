package com.dev.smartparking.ui.section

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.dev.smartparking.ui.theme.SmartParkingTheme

@Composable
fun InfoTicketSection(
    modifier: Modifier = Modifier,
    title: String,
    content: @Composable () -> Unit
) {
    Column {
        Text(
            text = title,
            style = TextStyle(
                fontSize = 16.sp
            )
        )
        content()
    }
}

@Preview(showBackground = true)
@Composable
private fun InfoTicketSectionPreview() {
    SmartParkingTheme {
        InfoTicketSection(
            title = "Slot"
        ) {
            Text(
                text = "F1 - A8",
                style = TextStyle(
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}
package com.dev.smartparking.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dev.smartparking.ui.card.MenuProfileCard
import com.dev.smartparking.ui.card.ProfileCard
import com.dev.smartparking.ui.theme.SmartParkingTheme

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Column (modifier = modifier) {
        ProfileCard()
        MenuProfileCard()
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    SmartParkingTheme {
        ProfileScreen()
    }
}
package com.dev.smartparking.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dev.smartparking.ui.theme.SmartParkingTheme

@Composable
fun BottomNavigationBarComponent(modifier: Modifier = Modifier) {
}

@Preview
@Composable
private fun BottomNavigationBarComponentPreview() {
    SmartParkingTheme {
        Scaffold(
            bottomBar = { BottomNavigationBarComponent() }
        ) { p ->
            Column (modifier = Modifier.padding(p)) {  }
        }
    }
}
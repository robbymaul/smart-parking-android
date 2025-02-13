package com.dev.smartparking.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.smartparking.R
import com.dev.smartparking.ui.component.ButtonComponent
import com.dev.smartparking.ui.component.TextFieldOtpComponent
import com.dev.smartparking.ui.section.IntroSection
import com.dev.smartparking.ui.theme.SmartParkingTheme

@Composable
fun OtpScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth()
            .fillMaxHeight().padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        IntroSection(
            title = R.string.title_screen_otp,
            description = R.string.desc_screen_otp,

        )
        TextFieldOtpComponent()
        ButtonComponent(
            text = R.string.txt_button_verify_otp,
            textColor = MaterialTheme.colorScheme.background,
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 8.dp,
                    end = 8.dp,
                    top = 16.dp,
                )
                .height(42.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun OtpScreenPreview() {
    SmartParkingTheme {
        OtpScreen()
    }
}
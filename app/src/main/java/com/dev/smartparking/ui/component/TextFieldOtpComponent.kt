package com.dev.smartparking.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.smartparking.ui.element.TextFieldOtpElement
import com.dev.smartparking.ui.theme.SmartParkingTheme

@Composable
fun TextFieldOtpComponent(modifier: Modifier = Modifier) {
    val focusRequesters = List(size = 4) {
        FocusRequester()
    }
    var otp1 by remember { mutableStateOf("") }

    Row (
        modifier = modifier.fillMaxWidth().
        padding(8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextFieldOtpElement(
            value = otp1,
            onValueChange = {newValue ->
                otp1 = newValue
            },
            focusRequester = focusRequesters[0]
        )
        TextFieldOtpElement(
            value = "",
            onValueChange = {},
            focusRequester = focusRequesters[1]
        )
        TextFieldOtpElement(
            value = "",
            onValueChange = {},
            focusRequester = focusRequesters[2]
        )
        TextFieldOtpElement(
            value = "",
            onValueChange = {},
            focusRequester = focusRequesters[3]
        )
    }
}

@Preview
@Composable
private fun TextFieldOtpPreview() {
    SmartParkingTheme {
        TextFieldOtpComponent()
    }
}
package com.dev.smartparking.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.smartparking.ui.element.TextFieldOtpElement
import com.dev.smartparking.ui.theme.SmartParkingTheme
import com.dev.smartparking.viewmodel.OTPViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun TextFieldOtpComponent(modifier: Modifier = Modifier, otpViewModel: OTPViewModel) {
    val otp1 = otpViewModel.otp1
    val otp2 = otpViewModel.otp2
    val otp3 = otpViewModel.otp3
    val otp4 = otpViewModel.otp4
    val otp5 = otpViewModel.otp5
    val otp6 = otpViewModel.otp6
    val pattern = remember { Regex("^\\d+$") }
    val focusManager = LocalFocusManager.current

    // Fokus per field
    val item1 = remember { FocusRequester() }
    val item2 = remember { FocusRequester() }
    val item3 = remember { FocusRequester() }
    val item4 = remember { FocusRequester() }
    val item5 = remember { FocusRequester() }
    val item6 = remember { FocusRequester() }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextFieldOtpElement(
            modifier = Modifier
                .focusRequester(item1)
                .focusProperties {
                    next = item2
                },
            value = otp1,
            onValueChange = { if (it.isEmpty() || it.matches(pattern)) otpViewModel.otp1Change(it) },
            focusManager = focusManager,
        )
        TextFieldOtpElement(
            modifier = Modifier
                .focusRequester(item2)
                .focusProperties {
                    next = item3
                    previous = item1
                },
            value = otp2,
            onValueChange = { if (it.isEmpty() || it.matches(pattern)) otpViewModel.otp2Change(it) },
            focusManager = focusManager,
            onBackspaceEmpty = { item1.requestFocus() }
        )
        TextFieldOtpElement(
            modifier = Modifier
                .focusRequester(item3)
                .focusProperties {
                    next = item4
                    previous = item2
                },
            value = otp3,
            onValueChange = { if (it.isEmpty() || it.matches(pattern)) otpViewModel.otp3Change(it) },
            focusManager = focusManager,
            onBackspaceEmpty = { item2.requestFocus() }
        )
        TextFieldOtpElement(
            modifier = Modifier
                .focusRequester(item4)
                .focusProperties {
                    next = item5
                    previous = item3
                },
            value = otp4,
            onValueChange = { if (it.isEmpty() || it.matches(pattern)) otpViewModel.otp4Change(it) },
            focusManager = focusManager,
            onBackspaceEmpty = { item3.requestFocus() }
        )
        TextFieldOtpElement(
            modifier = Modifier
                .focusRequester(item5)
                .focusProperties {
                    next = item6
                    previous = item4
                },
            value = otp5,
            onValueChange = { if (it.isEmpty() || it.matches(pattern)) otpViewModel.otp5Change(it) },
            focusManager = focusManager,
            onBackspaceEmpty = { item4.requestFocus() }
        )
        TextFieldOtpElement(
            modifier = Modifier
                .focusRequester(item6)
                .focusProperties {
                    previous = item5
                },
            value = otp6,
            onValueChange = { if (it.isEmpty() || it.matches(pattern)) otpViewModel.otp6Change(it) },
            focusManager = focusManager,
            onBackspaceEmpty = { item5.requestFocus() }
        )
    }
}


@Preview
@Composable
private fun TextFieldOtpPreview() {
    SmartParkingTheme {
        TextFieldOtpComponent(otpViewModel = koinViewModel())
    }
}
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
import com.dev.smartparking.viewmodel.ForgotPasswordViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun TextFieldForgotPasswordOtpComponent(modifier: Modifier = Modifier, forgotPasswordViewModel: ForgotPasswordViewModel) {
    val otp1 = forgotPasswordViewModel.otp1
    val otp2 = forgotPasswordViewModel.otp2
    val otp3 = forgotPasswordViewModel.otp3
    val otp4 = forgotPasswordViewModel.otp4
    val otp5 = forgotPasswordViewModel.otp5
    val otp6 = forgotPasswordViewModel.otp6
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
            onValueChange = { if (it.isEmpty() || it.matches(pattern)) forgotPasswordViewModel.otp1Change(it) },
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
            onValueChange = { if (it.isEmpty() || it.matches(pattern)) forgotPasswordViewModel.otp2Change(it) },
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
            onValueChange = { if (it.isEmpty() || it.matches(pattern)) forgotPasswordViewModel.otp3Change(it) },
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
            onValueChange = { if (it.isEmpty() || it.matches(pattern)) forgotPasswordViewModel.otp4Change(it) },
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
            onValueChange = { if (it.isEmpty() || it.matches(pattern)) forgotPasswordViewModel.otp5Change(it) },
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
            onValueChange = { if (it.isEmpty() || it.matches(pattern)) forgotPasswordViewModel.otp6Change(it) },
            focusManager = focusManager,
            onBackspaceEmpty = { item5.requestFocus() }
        )
    }
}


@Preview
@Composable
private fun TextFieldOtpPreview() {
    SmartParkingTheme {
        TextFieldForgotPasswordOtpComponent(forgotPasswordViewModel = koinViewModel())
    }
}
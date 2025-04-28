package com.dev.smartparking.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dev.smartparking.R
import com.dev.smartparking.route.Screen
import com.dev.smartparking.ui.component.DialogComponent
import com.dev.smartparking.ui.component.DialogVariant
import com.dev.smartparking.ui.component.LoadingButton
import com.dev.smartparking.ui.component.TextFieldForgotPasswordOtpComponent
import com.dev.smartparking.ui.section.IntroSection
import com.dev.smartparking.ui.theme.SmartParkingTheme
import com.dev.smartparking.viewmodel.ForgotPasswordViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun ForgotPasswordOtpScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    forgotPasswordViewModel: ForgotPasswordViewModel
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Kode OTP dikirim ke nomor: ${forgotPasswordViewModel.forgotPasswordModel?.phoneNumber ?: "-"}",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        IntroSection(
            title = stringResource(R.string.title_screen_otp),
            description = stringResource(R.string.desc_screen_otp),

            )
        TextFieldForgotPasswordOtpComponent(
            forgotPasswordViewModel = forgotPasswordViewModel
        )

        LoadingButton(
            text = R.string.txt_button_verify_otp,
            onClick = {
                forgotPasswordViewModel.forgotPasswordVerifyOtp {
                    forgotPasswordViewModel.viewModelScope.launch {
                        delay(1000)
                    }

                    navController.navigate(Screen.ResetPassword.route)
                }
            },
            isLoading = forgotPasswordViewModel.isLoading
        )
    }

    DialogComponent(
        open = forgotPasswordViewModel.isForgotPasswordVerifyOtpSuccessful,
        onClose = {
            forgotPasswordViewModel.isForgotPasswordVerifyOtpSuccessfulChange(false)
        },
        title = "OTP",
        description = "OTP berhasil di verifikasi",
        variant = DialogVariant.SUCCESS,
    )

    DialogComponent(
        open = forgotPasswordViewModel.isForgotPasswordVerifyOtpFailed,
        onClose = {
            forgotPasswordViewModel.isForgotPasswordVerifyOtpFailedChange(false)
        },
        title = "OTP",
        description = forgotPasswordViewModel.errorMessage,
        variant = DialogVariant.ERROR,
    )
}

@Preview(showBackground = true)
@Composable
private fun OtpScreenPreview() {
    SmartParkingTheme {
        OtpScreen(navController = rememberNavController(), otpViewModel = koinViewModel())
    }
}
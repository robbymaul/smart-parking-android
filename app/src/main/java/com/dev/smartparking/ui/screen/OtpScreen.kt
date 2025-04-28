package com.dev.smartparking.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dev.smartparking.R
import com.dev.smartparking.data.local.datastore.AuthPreferences
import com.dev.smartparking.route.Screen
import com.dev.smartparking.ui.component.ButtonComponent
import com.dev.smartparking.ui.component.DialogComponent
import com.dev.smartparking.ui.component.DialogVariant
import com.dev.smartparking.ui.component.LoadingButton
import com.dev.smartparking.ui.component.ResendOtpButton
import com.dev.smartparking.ui.component.TextFieldOtpComponent
import com.dev.smartparking.ui.element.LoadingDialog
import com.dev.smartparking.ui.section.IntroSection
import com.dev.smartparking.ui.theme.SmartParkingTheme
import com.dev.smartparking.viewmodel.OTPViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
fun OtpScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    otpViewModel: OTPViewModel
) {
    val authPreferences = koinInject<AuthPreferences>()

    val phoneNumberState = produceState<String?>(initialValue = null) {
        value = authPreferences.userPhoneNumber.first()
    }

    LaunchedEffect(Unit) {
        Log.d("LaunchedEffect", "triggered")
        val phone = authPreferences.userPhoneNumber.first()
        otpViewModel.phoneNumberChange(phone)
        otpViewModel.sendOtp()
    }


    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Kode OTP dikirim ke nomor: ${phoneNumberState.value ?: "-"}",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        IntroSection(
            title = stringResource(R.string.title_screen_otp),
            description = stringResource(R.string.desc_screen_otp),

        )
        TextFieldOtpComponent(
            otpViewModel = otpViewModel
        )

        LoadingButton(
            text = R.string.txt_button_verify_otp,
            onClick = {
                otpViewModel.verifyOtp() {
                    otpViewModel.viewModelScope.launch {
                        delay(1000)
                    }

                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.OTP.route) { inclusive = true }
                    }
                }
            },
            isLoading = otpViewModel.isLoading
        )

        ResendOtpButton {
            otpViewModel.resendOtp()
        }
    }


    LoadingDialog(otpViewModel.isLoading)

    DialogComponent(
        open = otpViewModel.isResendOTPSuccessful,
        onClose = {
            otpViewModel.isResendOTPSuccessfulChange(false)
        },
        title = "OTP",
//        description = "OTP Berhasil di kirim",
        description = otpViewModel.resendOTPMessage,
        variant = DialogVariant.SUCCESS,
    )

    DialogComponent(
        open = otpViewModel.isVerifyOTPSuccessful,
        onClose = {
            otpViewModel.isVerifyOTPSuccessfulChange(false)
        },
        title = "OTP",
        description = "OTP berhasil di verifikasi",
//        description = otpViewModel.resendOTPMessage,
        variant = DialogVariant.SUCCESS,
    )

    DialogComponent(
        open = otpViewModel.isVerifyOTPFailed,
        onClose = {
            otpViewModel.isVerifyOTPFailedChange(false)
        },
        title = "OTP",
        description = otpViewModel.errorMessage,
//        description = otpViewModel.resendOTPMessage,
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
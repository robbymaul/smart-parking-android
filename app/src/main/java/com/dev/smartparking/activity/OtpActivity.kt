package com.dev.smartparking.activity

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dev.smartparking.ui.screen.OtpScreen
import com.dev.smartparking.ui.theme.SmartParkingTheme
import com.dev.smartparking.viewmodel.OTPViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun OtpActivity(modifier: Modifier = Modifier, navController: NavHostController) {
    val otpViewModel: OTPViewModel = koinViewModel()

    OtpScreen(
        modifier = modifier,
        navController = navController,
        otpViewModel = otpViewModel
    )
}

@Preview(showBackground = true)
@Composable
private fun OtpActivityPreview() {
    SmartParkingTheme {
        OtpActivity(navController =  rememberNavController())
    }
}
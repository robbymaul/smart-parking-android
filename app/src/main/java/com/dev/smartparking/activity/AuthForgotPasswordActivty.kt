package com.dev.smartparking.activity

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dev.smartparking.R
import com.dev.smartparking.route.Screen
import com.dev.smartparking.ui.component.TopBarComponent
import com.dev.smartparking.ui.screen.ForgotPasswordOtpScreen
import com.dev.smartparking.ui.screen.ForgotPasswordScreen
import com.dev.smartparking.ui.screen.ResetPasswordScreen
import com.dev.smartparking.viewmodel.ForgotPasswordViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AuthForgotPasswordActivity(modifier: Modifier = Modifier, navController: NavHostController) {
    val forgotPasswordViewModel: ForgotPasswordViewModel = koinViewModel()
    val navForgotPasswordController =  rememberNavController()

    Scaffold(
        modifier = modifier,
        topBar = { TopBarComponent(
            onClickIcon = {
                forgotPasswordViewModel.resetStated()
                navController.popBackStack()
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Kembali",
                        tint = Color.White
                    )
                }
            },
            title = stringResource(R.string.txt_app_bar_forgot_password)
        ) },
    ) { innerPadding ->
        NavHost(
            navController = navForgotPasswordController,
            startDestination = Screen.ForgotPassword.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.ForgotPassword.route) {
                ForgotPasswordScreen(
                    navController = navForgotPasswordController,
                    forgotPasswordViewModel = forgotPasswordViewModel
                )
            }
            composable(Screen.ForgotPasswordOTP.route) {
                ForgotPasswordOtpScreen(
                    navController = navForgotPasswordController,
                    forgotPasswordViewModel = forgotPasswordViewModel
                )
            }
            composable(Screen.ResetPassword.route) {
                ResetPasswordScreen(
                    navController = navForgotPasswordController,
                    forgotPasswordViewModel = forgotPasswordViewModel,
                    mainNavController = navController
                )
            }
        }
    }
}
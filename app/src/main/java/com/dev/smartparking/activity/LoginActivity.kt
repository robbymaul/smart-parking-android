package com.dev.smartparking.activity

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.dev.smartparking.ui.screen.LoginScreen
import com.dev.smartparking.viewmodel.LoginViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginActivity(modifier: Modifier = Modifier, navController: NavHostController) {
    val loginViewModel: LoginViewModel = koinViewModel()

    LoginScreen(
        modifier = modifier,
        navController = navController,
        loginViewModel = loginViewModel
    )
}
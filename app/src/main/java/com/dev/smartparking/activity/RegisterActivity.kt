package com.dev.smartparking.activity

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.dev.smartparking.ui.screen.RegisterScreen
import com.dev.smartparking.viewmodel.RegisterViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterActivity(modifier: Modifier = Modifier, navController: NavHostController) {
    val registerViewModel: RegisterViewModel = koinViewModel()

    RegisterScreen(
        modifier = modifier,
        navController = navController,
        registerViewModel = registerViewModel
    )
}
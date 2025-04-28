package com.dev.smartparking.activity

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.dev.smartparking.ui.screen.StnkActivationScreen
import com.dev.smartparking.ui.theme.SmartParkingTheme
import com.dev.smartparking.viewmodel.STNKActivationViewModel

@Composable
fun ActivationActivity(modifier: Modifier = Modifier, navController: NavHostController?) {
    val stnkActivationViewModel: STNKActivationViewModel = viewModel()

    StnkActivationScreen(
        modifier = modifier,
        navController = navController,
        stnkActivationViewModel = stnkActivationViewModel
    )
}

@Preview(showBackground = true)
@Composable
private fun ActivationActivityPreview() {
    SmartParkingTheme {
        ActivationActivity(
            navController = null
        )
    }
}
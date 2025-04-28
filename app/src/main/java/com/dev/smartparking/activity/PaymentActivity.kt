package com.dev.smartparking.activity

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.dev.smartparking.ui.component.TopBarComponent
import com.dev.smartparking.ui.screen.PaymentScreen
import com.dev.smartparking.ui.theme.SmartParkingTheme
import com.dev.smartparking.viewmodel.PaymentViewModel

@Composable
fun PaymentActivity(
    modifier: Modifier = Modifier,
    navController: NavHostController? = null
) {

    val paymentViewModel: PaymentViewModel = viewModel()

    Scaffold (
        modifier = modifier,
        topBar = {
            TopBarComponent(
                title = "Payment",
                onClickIcon = { paymentViewModel.handleClickNavigationIcon(navController) }
            )
        }
    ) { innerPadding ->
        PaymentScreen(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            paymentViewModel
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PaymentActivityPreview() {
    SmartParkingTheme {
        PaymentActivity()
    }
}
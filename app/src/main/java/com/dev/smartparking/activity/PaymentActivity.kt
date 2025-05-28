package com.dev.smartparking.activity

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dev.smartparking.route.Screen
import com.dev.smartparking.ui.component.TopBarComponent
import com.dev.smartparking.ui.element.LoadingDialog
import com.dev.smartparking.ui.screen.PaymentScreen
import com.dev.smartparking.ui.theme.SmartParkingTheme
import com.dev.smartparking.viewmodel.PaymentViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun PaymentActivity(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    bookingId: Int
) {
    val paymentViewModel: PaymentViewModel = koinViewModel()

    LaunchedEffect(Unit) {
        paymentViewModel.getBooking(bookingId) {
            navController.navigate(Screen.Main.route) {
                popUpTo(
                    route = "${Screen.Payment.route}/${bookingId}"
                ) {
                    inclusive = true
                }
            }
        }
    }

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
            paymentViewModel,
        )
    }

    LoadingDialog(isLoading = paymentViewModel.isLoading)
}

@Preview(showBackground = true)
@Composable
private fun PaymentActivityPreview() {
    SmartParkingTheme {
        PaymentActivity(bookingId = 0, navController = rememberNavController())
    }
}
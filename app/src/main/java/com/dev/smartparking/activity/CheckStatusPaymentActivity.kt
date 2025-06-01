package com.dev.smartparking.activity

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.dev.smartparking.route.Screen
import com.dev.smartparking.ui.component.TopBarComponent
import com.dev.smartparking.ui.element.LoadingDialog
import com.dev.smartparking.ui.screen.CheckPaymentStatusScreen
import com.dev.smartparking.viewmodel.PaymentViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun CheckStatusPaymentActivity(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    bookingId: Int
) {
    val paymentViewModel: PaymentViewModel = koinViewModel()

    LaunchedEffect(Unit) {
        paymentViewModel.checkStatusPayment(bookingId, onSuccess = {}, onFailed = {})
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopBarComponent(
                title = "Payment",
                onClickIcon = {  }
            )
        }
    ) { innerPadding ->
        CheckPaymentStatusScreen(
            modifier = Modifier.padding(innerPadding),
            paymentStatusModel = paymentViewModel.paymentStatusModel
        ) {
            paymentViewModel.checkStatusPayment(bookingId, onSuccess = {
                if (paymentViewModel.paymentStatusModel?.paymentStatus == "completed") {
                    Log.d("trigger completed to detail ticker", "trigger ${paymentViewModel.paymentStatusModel?.paymentStatus}")
                    navController.navigate(
                        route = "${Screen.DetailTicket.route}/${bookingId}"
                    )
                }
            }, onFailed = {})
        }
    }

    LoadingDialog(isLoading = paymentViewModel.isLoading)
}

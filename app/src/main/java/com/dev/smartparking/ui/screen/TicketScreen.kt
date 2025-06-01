package com.dev.smartparking.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dev.smartparking.ui.card.TicketCard
import com.dev.smartparking.ui.component.MenuTicketComponent
import com.dev.smartparking.ui.theme.SmartParkingTheme
import com.dev.smartparking.viewmodel.TicketViewModel
import org.koin.androidx.compose.koinViewModel
import androidx.compose.foundation.lazy.items

@Composable
fun TicketScreen(
    modifier: Modifier = Modifier,
    mainNavController: NavHostController,
    ticketViewModel: TicketViewModel,
    bottomNavController: NavHostController
) {
    LaunchedEffect(Unit) {
        Log.d("launched effect ticket screen", "triggered")
        ticketViewModel.getListTicketBooking() {}
    }

    Column (
        modifier = modifier.fillMaxWidth()
    ) {
        MenuTicketComponent()
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items = ticketViewModel.ticketBookingModel, key = {it.id} ) {
                TicketCard(ticketBooking = it, mainNavController = mainNavController, bottomNavController = bottomNavController)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TicketScreenPreview() {
    SmartParkingTheme {
        TicketScreen(
            mainNavController = rememberNavController(),
            ticketViewModel = koinViewModel(),
            bottomNavController = rememberNavController()
        )
    }
}
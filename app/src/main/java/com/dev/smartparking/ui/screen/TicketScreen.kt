package com.dev.smartparking.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dev.smartparking.ui.card.TicketCard
import com.dev.smartparking.ui.component.MenuTicketComponent
import com.dev.smartparking.ui.theme.SmartParkingTheme

@Composable
fun TicketScreen(modifier: Modifier = Modifier, navController: NavHostController?) {
    Column (
        modifier = modifier.fillMaxWidth()
    ) {
        MenuTicketComponent()
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(7) {
                TicketCard()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TicketScreenPreview() {
    SmartParkingTheme {
        TicketScreen(navController = null)
    }
}
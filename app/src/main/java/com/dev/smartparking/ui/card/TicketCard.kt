package com.dev.smartparking.ui.card

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dev.smartparking.domain.model.TicketBooking
import com.dev.smartparking.route.Screen
import com.dev.smartparking.ui.element.HoursBookingElement
import com.dev.smartparking.ui.element.StatusTicketElement
import com.dev.smartparking.ui.element.TimeBookingElement
import com.dev.smartparking.ui.element.TimerTicketElement
import com.dev.smartparking.ui.section.InfoTicketSection
import com.dev.smartparking.ui.theme.SmartParkingTheme

@Composable
fun TicketCard(
    modifier: Modifier = Modifier,
    ticketBooking: TicketBooking,
    mainNavController: NavHostController,
    bottomNavController: NavHostController
) {
    var statusTicket by remember { mutableStateOf(ticketBooking.payment) }

    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = 6.dp,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .clickable {
                when (statusTicket) {
                    "pending" -> {
                        mainNavController.navigate(
                            route = "${Screen.Payment.route}/${ticketBooking.id}"
                        )
                    }
                    "expired" -> {
                    }
                    else -> {
                        mainNavController.navigate(route = "${Screen.DetailTicket.route}/${ticketBooking.id}")
                    }
                }
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            // Location & Status Row
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = ticketBooking.location,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = Color(0xFF333333)
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    StatusTicketElement(
                        status = ticketBooking.status,
                        payment = ticketBooking.payment
                    )
                    TimerTicketElement(
                        entry = ticketBooking.startTime,
                        exit = ticketBooking.endTime
                    ) {
                        statusTicket = it
                    }
                }
            }

            // Time & Duration
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                TimeBookingElement(
                    startTime = ticketBooking.startTime,
                    endTime = ticketBooking.endTime,
                    modifier = Modifier.weight(3f)
                )
                HoursBookingElement(
                    modifier = Modifier.weight(1f),
                    startTime = ticketBooking.startTime,
                    endTime = ticketBooking.endTime
                )
            }

            Divider(
                color = Color.LightGray.copy(alpha = 0.6f),
                thickness = 1.dp
            )

            // Info Section (Slot, License, Vehicle)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                InfoTicketSection(title = "Slot") {
                    Text(
                        text = ticketBooking.slotNumber,
                        style = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 20.sp,
                            color = Color(0xFF1E88E5)
                        )
                    )
                }
                InfoTicketSection(title = "License") {
                    Text(
                        text = ticketBooking.licencePlate,
                        style = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 20.sp,
                            color = Color(0xFF1E88E5)
                        )
                    )
                }
                InfoTicketSection(title = "Vehicle") {
                    Text(
                        text = ticketBooking.vehicle.ifBlank { ticketBooking.type },
                        style = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 20.sp,
                            color = Color(0xFF1E88E5)
                        )
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun TicketCardPreview() {
    SmartParkingTheme {
        TicketCard(
            ticketBooking = TicketBooking(
                id = 1,
                licencePlate = "",
                location = "",
                payment = "",
                slotNumber = "",
                startTime = "",
                endTime = "",
                status = "",
                type = "",
                vehicle = "",
            ),
            mainNavController = rememberNavController(),
            bottomNavController = rememberNavController()
        )
    }
}
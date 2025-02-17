package com.dev.smartparking.ui.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev.smartparking.ui.component.ButtonComponent
import com.dev.smartparking.ui.element.HoursBookingElement
import com.dev.smartparking.ui.element.StatusTicketElement
import com.dev.smartparking.ui.element.TimeBookingElement
import com.dev.smartparking.ui.element.TimerTicketElement
import com.dev.smartparking.ui.section.InfoTicketSection
import com.dev.smartparking.ui.theme.SmartParkingTheme

@Composable
fun TicketCard(modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = 8.dp, // Meningkatkan shadow agar lebih terlihat
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp) // Memberi ruang agar shadow terlihat
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 180.dp)
                .padding(16.dp), // Menambah padding agar isi tidak menempel ke tepi
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Margonda City Mall",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.End),
                    modifier = Modifier.wrapContentWidth()
                ) {
                    StatusTicketElement()
                    TimerTicketElement()
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                TimeBookingElement(
                    startTime = "10:00",
                    endTime = "12:00",
                    modifier = Modifier.weight(3f)
                )
                HoursBookingElement(
                    text = "2 Hours",
                    modifier = Modifier.weight(1f)
                )
            }
            Divider(color = Color.Gray, thickness = 1.dp)
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                InfoTicketSection(title = "Slot") {
                    Text(
                        text = "F1 - A8",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )
                    )
                }
                InfoTicketSection(title = "License") {
                    Text(
                        text = "B 7785 TY",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )
                    )
                }
                InfoTicketSection(title = "Vehicle") {
                    Text(
                        text = "Honda Jazz",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
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
        TicketCard()
    }
}
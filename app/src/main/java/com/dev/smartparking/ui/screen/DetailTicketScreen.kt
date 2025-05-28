package com.dev.smartparking.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dev.smartparking.R
import com.dev.smartparking.ui.card.OrderNumberCard
import com.dev.smartparking.ui.component.QRCodeGenerator
import com.dev.smartparking.ui.component.TopBarMenuHomepageComponent
import com.dev.smartparking.ui.section.ContentSection
import com.dev.smartparking.ui.theme.SmartParkingTheme
import com.dev.smartparking.viewmodel.DetailTicketViewModel
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailTicketScreen(
    modifier: Modifier = Modifier,
    initialStatus: String = "Parking",
    initialTime: Int = 5,
    navController: NavHostController,
    bookingId: Int
) {
    val detailTicketViewModel: DetailTicketViewModel = koinViewModel()

    LaunchedEffect(Unit) {
        detailTicketViewModel.getBooking(26) {}
    }

    var status by remember { mutableStateOf(initialStatus) }
    var remainingTime by remember { mutableStateOf(initialTime) } // Waktu dalam detik
    var chargeTime by remember { mutableStateOf(0) } // Jika waktu check-out lewat, mulai charge time
    var isQrVisible by remember { mutableStateOf(true) } // QR Code visibility
    var showChargeButton by remember { mutableStateOf(false) }

    if (status != "Canceled" && status != "Completed") {
        LaunchedEffect(remainingTime) {
            while (remainingTime > 0) {
                delay(1000L) // Kurangi setiap detik
                remainingTime--
            }
            if (remainingTime == 0) {
                if (status == "On Going") {
                    status = "Expired" // Tiket hangus saat check-in habis
                    isQrVisible = false
                } else if (status == "Parking" || status == "Overtime Charge") {
                    isQrVisible = false // QR Code hilang jika waktu checkout habis
                    while (true) { // Hitung maju charge time
                        showChargeButton = true
                        status = "Overtime Charge"
                        delay(1000L)
                        chargeTime++
                    }
                }
            }
        }
    }

    Scaffold(topBar = { TopBarMenuHomepageComponent() }) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.TopCenter),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxSize(),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (isQrVisible) {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                QRCodeGenerator(
                                    url = "http://example.com", id = "12345689"
                                )
                            }
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(
                                16.dp, alignment = Alignment.CenterHorizontally
                            )
                        ) {
                            when (status) {
                                "On Going" -> Text(text = "Time to Check-in")
                                "Parking" -> Text(text = "Time to Check-out")
                                "Expired" -> Text(text = "Ticket Expired")
                                "Overtime Charge" -> Text(text = "Overtime Charge")
                                else -> {}
                            }

                            if (status != "Canceled" && status != "Completed") {
                                Row {
                                    Icon(
                                        imageVector = Icons.Default.AccessTime,
                                        contentDescription = ""
                                    )
                                    Text(
                                        text = when {
                                            status == "Expired" -> "00:00"
                                            status == "Parking" && remainingTime == 0 -> String.format(
                                                "%02d:%02d", chargeTime / 60, chargeTime % 60
                                            )

                                            status == "Overtime Charge" && remainingTime == 0 -> String.format(
                                                "%02d:%02d", chargeTime / 60, chargeTime % 60
                                            )

                                            else -> String.format(
                                                "%02d:%02d", remainingTime / 60, remainingTime % 60
                                            )
                                        }
                                    )
                                }
                            }
                        }

                        OrderNumberCard(orderNumber = "", slotNumber = "")

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            ContentSection(
                                modifier = Modifier.weight(1f),
                                title = R.string.txt_payment,
                                {
                                    Text(text = "Paid")
                                },
                            )
                            ContentSection(
                                modifier = Modifier.weight(1f),
                                title = R.string.txt_status,
                                {
                                    Text(text = status)
                                },
                            )
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            ContentSection(
                                modifier = Modifier.weight(1f),
                                title = R.string.txt_name,
                                {
                                    Text(text = "Robby Maulana")
                                },
                            )
                            ContentSection(
                                modifier = Modifier.weight(1f),
                                title = R.string.txt_phone,
                                {
                                    Text(text = "0898778567656")
                                },
                            )
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            ContentSection(
                                modifier = Modifier.weight(1f),
                                title = R.string.txt_start_time,
                                {
                                    Text(text = "10:00")
                                },
                            )
                            ContentSection(
                                modifier = Modifier.weight(1f),
                                title = R.string.txt_end_time,
                                {
                                    Text(text = "12:00")
                                },
                            )
                        }

                        ContentSection(
                            modifier = Modifier.fillMaxWidth(),
                            title = R.string.txt_location,
                            {
                                Text(text = "Margonda City Mall")
                            },
                        )

                        ContentSection(
                            modifier = Modifier.fillMaxWidth(),
                            title = R.string.txt_address,
                            {
                                Text(text = "Jalan Margonda Raya Kota Depok Jawa Barat")
                            },
                        )
                    }


                }
            }
            when (status) {
                "Canceled" -> {
                    Image(
                        painter = painterResource(R.drawable.image_canceled1),
                        contentDescription = "",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .width(250.dp)
                            .height(250.dp),
                        alpha = 0.3f
                    )
                }

                "Completed" -> {
                    Image(
                        painter = painterResource(R.drawable.image_completed1),
                        contentDescription = "",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .width(250.dp)
                            .height(250.dp),
                        alpha = 0.3f
                    )
                }

                else -> {}
            }
            if (showChargeButton) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter) // Menjaga posisi tetap di bawah
                        .padding(8.dp), colors = CardDefaults.cardColors(
                        containerColor = Color.Black
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                modifier = Modifier.weight(1f),
                                text = stringResource(R.string.txt_total_charge),
                                color = Color.White
                            )
                            Text(
                                text = "-", color = Color.White
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = {},
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                        ) {
                            Text(
                                text = stringResource(id = R.string.txt_payment),
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun DetailTicketScreenPreview() {
    SmartParkingTheme {
        DetailTicketScreen(navController = rememberNavController(), bookingId = 1)
    }
}
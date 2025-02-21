package com.dev.smartparking.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowRight
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.smartparking.R
import com.dev.smartparking.ui.card.OrderNumberCard
import com.dev.smartparking.ui.component.ButtonComponent
import com.dev.smartparking.ui.component.TopBarMenuHomepageComponent
import com.dev.smartparking.ui.section.ContentSection
import com.dev.smartparking.ui.theme.SmartParkingTheme

@Composable
fun PaymentScreen(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = { TopBarMenuHomepageComponent() }
    ) { innerPadding ->
        Column (
            modifier = modifier.padding(innerPadding)
                .fillMaxSize()
        ) {
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxSize()
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item { OrderNumberCard() }

                    // Baris 1
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            ContentSection(
                                modifier = Modifier.weight(1f),
                                title = R.string.txt_name
                            ) {
                                Text(
                                    text = "Robby Maulana"
                                )
                            }
                            ContentSection(
                                modifier = Modifier.weight(1f),
                                title = R.string.txt_phone
                            ) {
                                Text(text = "Robby Maulana")
                            }
                        }
                    }

                    // Baris 2
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            ContentSection(
                                modifier = Modifier.weight(1f),
                                title = R.string.txt_vehicle
                            ) {
                                Text(text = "Robby Maulana")
                            }
                            ContentSection(
                                modifier = Modifier.weight(1f),
                                title = R.string.txt_vehicle_number
                            ) {
                                Text(text = "Robby Maulana")
                            }
                        }
                    }

                    // Baris 3
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            ContentSection(
                                modifier = Modifier.weight(1f),
                                title = R.string.txt_start_time
                            ) {
                                Text(text = "Robby Maulana")
                            }
                            ContentSection(
                                modifier = Modifier.weight(1f),
                                title = R.string.txt_end_time
                            ) {
                                Text(text = "Robby Maulana")
                            }
                        }
                    }

                    item {
                        ContentSection(
                            modifier = Modifier.fillMaxWidth(),
                            title = R.string.txt_location
                        ) {
                            Text(text = "Robby Maulana")
                        }
                    }

                    item {
                        ContentSection(
                            modifier = Modifier.fillMaxWidth(),
                            title = R.string.txt_address
                        ) {
                            Text(text = "Robby Maulana")
                        }
                    }
                    item {
                        ContentSection(
                            modifier = Modifier.weight(1f),
                            title = R.string.txt_payment_method // Pastikan string ini sesuai dengan kebutuhan
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        // Aksi ketika diklik (misalnya buka dialog pilih pembayaran)
                                    },
                            ) {
                                Text(
                                    text = "Pilih Pembayaran",
                                    color = MaterialTheme.colorScheme.primary, // Bisa diubah sesuai tema
                                    fontWeight = FontWeight.Bold
                                )
                                Icon(
                                    imageVector = Icons.AutoMirrored.Default.ArrowRight, // Ikon tanda ">"
                                    contentDescription = "Pilih pembayaran",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }

                    item {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            colors = CardDefaults.cardColors(
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
                                        text = stringResource(R.string.txt_admin),
                                        color = Color.White
                                    )
                                    Text(
                                        text = "-",
                                        color = Color.White
                                    ) // Warna teks putih
                                }
                                Row(modifier = Modifier.fillMaxWidth()) {
                                    Text(
                                        text = stringResource(R.string.txt_discount),
                                        modifier = Modifier.weight(1f),
                                        color = Color.White
                                    )
                                    Text(
                                        text = "-",
                                        color = Color.White
                                    )
                                }
                                Row(modifier = Modifier.fillMaxWidth()) {
                                    Text(
                                        text = stringResource(R.string.txt_total),
                                        modifier = Modifier.weight(1f),
                                        color = Color.White
                                    )
                                    Text(
                                        text = "-",
                                        color = Color.White
                                    )
                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                Button(
                                    onClick = {},
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                                ) {
                                    Text(
                                        text = stringResource(id = R.string.txt_button_book_now),
                                        color = Color.Black
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PaymentScreenPreview() {
    SmartParkingTheme {
        PaymentScreen()
    }
}
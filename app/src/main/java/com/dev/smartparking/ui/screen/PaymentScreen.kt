package com.dev.smartparking.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dev.smartparking.R
import com.dev.smartparking.data.utils.formatToWIBDateTime
import com.dev.smartparking.domain.model.PaymentMethod
import com.dev.smartparking.route.Screen
import com.dev.smartparking.ui.card.OrderNumberCard
import com.dev.smartparking.ui.component.DialogAction
import com.dev.smartparking.ui.component.DialogComponent
import com.dev.smartparking.ui.component.DialogVariant
import com.dev.smartparking.ui.section.ContentSection
import com.dev.smartparking.ui.theme.SmartParkingTheme
import com.dev.smartparking.viewmodel.PaymentViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun PaymentScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    paymentViewModel: PaymentViewModel,
) {
    val showSheet = remember { mutableStateOf(false) }
    val selectedPayment = remember { mutableStateOf<PaymentMethod?>(null) }

    Column(
        modifier = modifier
            .padding(8.dp)
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
                item {
                    OrderNumberCard(
                        orderNumber = paymentViewModel.bookingModel?.bookingReference ?: "",
                        slotNumber = paymentViewModel.bookingModel?.slotNumber ?: ""
                    )
                }

                // Baris 1
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        ContentSection(
                            modifier = Modifier.weight(1f),
                            title = R.string.txt_name,
                            {
                                Text(
                                    text = paymentViewModel.bookingModel?.name ?: ""
                                )
                            },
                        )
                        ContentSection(
                            modifier = Modifier.weight(1f),
                            title = R.string.txt_phone,
                            {
                                Text(text = paymentViewModel.bookingModel?.phoneNumber ?: "")
                            },
                        )
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
                            title = R.string.txt_vehicle,
                            {
                                Text(text = paymentViewModel.bookingModel?.vehicle ?: "")
                            },
                        )
                        ContentSection(
                            modifier = Modifier.weight(1f),
                            title = R.string.txt_vehicle_number,
                            {
                                Text(text = paymentViewModel.bookingModel?.licencePlate ?: "")
                            },
                        )
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
                            title = R.string.txt_start_time,
                        ) {
                            Text(text = formatToWIBDateTime(paymentViewModel.bookingModel?.startTime))
                        }
                        ContentSection(
                            modifier = Modifier.weight(1f),
                            title = R.string.txt_end_time,
                        ) {
                            Text(text = formatToWIBDateTime(paymentViewModel.bookingModel?.endTime))
                        }
                    }
                }

                item {
                    ContentSection(
                        modifier = Modifier.fillMaxWidth(),
                        title = R.string.txt_location,
                        {
                            Text(text = paymentViewModel.bookingModel?.location ?: "")
                        },

                        )
                }

                item {
                    ContentSection(
                        modifier = Modifier.fillMaxWidth(),
                        title = R.string.txt_address,
                        {
                            Text(text = paymentViewModel.bookingModel?.address ?: "")
                        },

                        )
                }
                item {
                    ContentSection(
                        modifier = Modifier.weight(1f),
                        title = R.string.txt_payment_method,
                        {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        // Aksi ketika diklik (misalnya buka dialog pilih pembayaran)
                                        paymentViewModel.getListPaymentMethod {}
                                        showSheet.value = true
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
                        },
                    )
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
                                    text = "${paymentViewModel.bookingModel?.adminFee ?: 0}",
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
                                    text = "${paymentViewModel.bookingModel?.discount ?: 0}",
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
                                    text = "${paymentViewModel.bookingModel?.estimatedPrice ?: 0}",
                                    color = Color.White
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Button(
                                onClick = {
//                                    paymentViewModel.handleClickPayment(navController)

                                    navController.navigate(route = "${Screen.CheckStatusPayment.route}/${29}")

                                    paymentViewModel.payment(
                                        bookingId = paymentViewModel.bookingModel?.id ?: 0,
                                        paymentMethodId = selectedPayment.value?.id ?: 0,
                                        onSuccess = {},
                                        onFailed = {},
                                        )
                                },
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.txt_button_payment),
                                    color = Color.Black
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    if (showSheet.value) {
        PaymentMethodBottomSheet(
            paymentMethods = paymentViewModel.paymentMethodModel,
            selectedKey = selectedPayment.value?.id,
            onSelected = {
                selectedPayment.value = it
                showSheet.value = false
            },
            onDismiss = {
                showSheet.value = false
            }
        )
    }

    DialogComponent(
        open = paymentViewModel.isGetListPaymentMethodFailed,
        onClose = {
            paymentViewModel.onIsGetListPaymentMethodFailedChange(false)
        },
        title = "Metode Pembayaran",
        description = paymentViewModel.errorMessage,
        variant = DialogVariant.ERROR,
        actions = listOf(
            DialogAction(label = "Tutup", onClick = {
                paymentViewModel.onIsGetListPaymentMethodFailedChange(false)
            }),
        )
    )

    DialogComponent(
        open = paymentViewModel.isPaymentFailed,
        onClose = {
            paymentViewModel.onIsPaymentFailedChange(false)
        },
        title = "Pembayaran",
        description = paymentViewModel.errorMessage,
        variant = DialogVariant.ERROR,
        actions = listOf(
            DialogAction(label = "Tutup", onClick = {
                paymentViewModel.onIsPaymentFailedChange(false)
            }),
        )
    )

    DialogComponent(
        open = paymentViewModel.isPaymentSuccessful,
        onClose = {
            paymentViewModel.onIsPaymentSuccessfulChange(false)
        },
        title = "Pembayaran",
        description = "Berhasil",
        variant = DialogVariant.SUCCESS,
    )

}

@Preview(showBackground = true)
@Composable
private fun PaymentScreenPreview() {
    SmartParkingTheme {
        PaymentScreen(
            navController = rememberNavController(),
            paymentViewModel = koinViewModel(),
        )
    }
}
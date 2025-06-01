package com.dev.smartparking.ui.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.dev.smartparking.R
import com.dev.smartparking.data.utils.extractTimeOnly
import com.dev.smartparking.ui.card.OrderNumberCard
import com.dev.smartparking.ui.component.QRCodeGenerator
import com.dev.smartparking.ui.component.TopBarComponent
import com.dev.smartparking.ui.component.TopBarMenuHomepageComponent
import com.dev.smartparking.ui.section.ContentSection
import com.dev.smartparking.ui.theme.SmartParkingTheme
import com.dev.smartparking.viewmodel.TicketViewModel
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel
import java.time.Duration
import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@Composable
fun DetailTicketScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    bookingId: Int
) {
    val detailTicketViewModel: TicketViewModel = koinViewModel()
    val context = LocalContext.current

    var status by remember { mutableStateOf<String?>(null) }
    var remainingTime by remember { mutableStateOf(0) }
    var chargeTime by remember { mutableStateOf(0) }
    var isQrVisible by remember { mutableStateOf(false) }
    var showChargeButton by remember { mutableStateOf(false) }

    val booking = detailTicketViewModel.bookingModel

    var startCountdown by remember { mutableStateOf(false) }
    var startCharge by remember { mutableStateOf(false) }

    // Fetch booking data
    LaunchedEffect(bookingId) {
        detailTicketViewModel.getBooking(bookingId) {
            booking?.let {
                status = it.status

                val now = Instant.now()
                val start = Instant.parse(it.startTime)
                val end = Instant.parse(it.endTime)

                // Set QR code visibility based on status
                isQrVisible = it.status in listOf("confirmed", "parking")

                when (it.status) {
                    "confirmed" -> {
                        remainingTime =
                            Duration.between(now, start).seconds.toInt().coerceAtLeast(0)
                        startCountdown = true
                    }

                    "parking" -> {
                        remainingTime = Duration.between(now, end).seconds.toInt().coerceAtLeast(0)
                        startCountdown = true
                    }
                }
            }
        }
    }

    // Countdown
    LaunchedEffect(startCountdown) {
        if (startCountdown && (status == "confirmed" || status == "parking")) {
            while (remainingTime > 0) {
                delay(1000L)
                remainingTime--
            }

            if (status == "confirmed") {
                status = "expired"
                isQrVisible = false
            } else if (status == "parking") {
                status = "overtime charge"
                showChargeButton = true
                startCharge = true
            }
        }
    }

    // Charge timer
    LaunchedEffect(startCharge) {
        if (startCharge) {
            while (true) {
                delay(1000L)
                chargeTime++
            }
        }
    }

    Scaffold(
        topBar = {
            TopBarComponent(
                title = stringResource(R.string.txt_app_bar_forgot_ticket),
                onClickIcon = {},
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter),
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
//                        if (isQrVisible) {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                AsyncImage(
                                    model = booking?.qrCode,
                                    contentDescription = "tiket qr code",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(240.dp)
//                                        .clip(CircleShape)
                                        .background(Color.LightGray),
                                    onSuccess = { Log.d("ImageLoad", "Image loaded successfully") },
                                    onError = {
                                        Log.e(
                                            "ImageLoad",
                                            "Error loading image: ${it.result.throwable}"
                                        )
                                    }
                                )
//                            }
                        }

//                        Row(
//                            modifier = Modifier.fillMaxWidth(),
//                            horizontalArrangement = Arrangement.spacedBy(
//                                16.dp,
//                                Alignment.CenterHorizontally
//                            )
//                        ) {
//                            Text(
//                                text = when (status) {
//                                    "confirmed" -> "Time to Check-in"
//                                    "parking" -> "Time to Check-out"
//                                    "expired" -> "Ticket Expired"
//                                    "overtime charge" -> "Overtime Charge"
//                                    else -> ""
//                                }
//                            )
//
//                            if (status != "canceled" && status != "completed") {
//                                Row {
//                                    Icon(
//                                        imageVector = Icons.Default.AccessTime,
//                                        contentDescription = ""
//                                    )
//                                    Text(
//                                        text = when (status) {
//                                            "overtime charge" -> String.format(
//                                                "%02d:%02d",
//                                                chargeTime / 60,
//                                                chargeTime % 60
//                                            )
//
//                                            else -> String.format(
//                                                "%02d:%02d",
//                                                remainingTime / 60,
//                                                remainingTime % 60
//                                            )
//                                        }
//                                    )
//                                }
//                            }
//                        }

                        OrderNumberCard(
                            orderNumber = booking?.bookingReference ?: "",
                            slotNumber = booking?.slotNumber ?: ""
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            ContentSection(
                                modifier = Modifier.weight(1f),
                                title = R.string.txt_payment
                            ) {
                                Text(text = booking?.payment ?: "")
                            }
                            ContentSection(
                                modifier = Modifier.weight(1f),
                                title = R.string.txt_status
                            ) {
                                Text(text = booking?.status ?: "")
                            }
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            ContentSection(
                                modifier = Modifier.weight(1f),
                                title = R.string.txt_name
                            ) {
                                Text(text = booking?.name ?: "")
                            }
                            ContentSection(
                                modifier = Modifier.weight(1f),
                                title = R.string.txt_phone
                            ) {
                                Text(text = booking?.phoneNumber ?: "")
                            }
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            ContentSection(
                                modifier = Modifier.weight(1f),
                                title = R.string.txt_start_time
                            ) {
                                Text(text = extractTimeOnly(booking?.startTime ?: ""))
                            }
                            ContentSection(
                                modifier = Modifier.weight(1f),
                                title = R.string.txt_end_time
                            ) {
                                Text(text = extractTimeOnly(booking?.endTime ?: ""))
                            }
                        }

                        ContentSection(
                            modifier = Modifier.fillMaxWidth(),
                            title = R.string.txt_location
                        ) {
                            Text(text = booking?.location ?: "")
                        }

                        ContentSection(
                            modifier = Modifier.fillMaxWidth(),
                            title = R.string.txt_address
                        ) {
                            Text(text = booking?.address ?: "")
                        }
                    }
                }
            }

            when (status) {
                "canceled" -> {
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

                "completed" -> {
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
            }

            if (showChargeButton) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Black)
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
                            Text(text = "-", color = Color.White)
                        }

                        Button(
                            onClick = { /* TODO: handle payment */ },
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


fun calculateRemainingSeconds(targetTime: String): Int {
    return try {
        val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
        val now = OffsetDateTime.now(ZoneOffset.UTC)
        val target = OffsetDateTime.parse(targetTime, formatter)
        val duration = Duration.between(now, target)
        duration.seconds.toInt().coerceAtLeast(0)
    } catch (e: Exception) {
        0 // Fallback ke 0 jika parsing gagal
    }
}


@Preview(showBackground = true)
@Composable
private fun DetailTicketScreenPreview() {
    SmartParkingTheme {
        DetailTicketScreen(navController = rememberNavController(), bookingId = 1)
    }
}
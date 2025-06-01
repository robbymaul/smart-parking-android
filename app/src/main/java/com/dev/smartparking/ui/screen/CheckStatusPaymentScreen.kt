package com.dev.smartparking.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev.smartparking.domain.model.PaymentStatus

@Composable
fun CheckPaymentStatusScreen(
    modifier: Modifier = Modifier,
    paymentStatusModel: PaymentStatus?,
    onRefreshStatus: () -> Unit,
) {
    val clipboardManager = LocalClipboardManager.current
    val context = LocalContext.current

    if (paymentStatusModel == null) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Data pembayaran tidak ditemukan")
        }
        return
    }

    // Ambil data dari model
    val status = paymentStatusModel.paymentStatus
    val amount = paymentStatusModel.amount
    val paymentReference = paymentStatusModel.paymentReference

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Status Pembayaran", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = when (status.lowercase()) {
                "completed" -> "Pembayaran Berhasil"
                "failed" -> "Pembayaran Gagal"
                "pending" -> "Menunggu Pembayaran"
                else -> "Status tidak diketahui"
            },
            color = when (status.lowercase()) {
                "completed" -> Color(0xFF4CAF50) // hijau
                "failed" -> Color(0xFFF44336)    // merah
                "pending" -> Color(0xFFFB8C00)   // oranye
                else -> Color.Gray
            },
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text("Kode Pembayaran / Referensi", fontWeight = FontWeight.Bold)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(paymentReference, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = {
                clipboardManager.setText(AnnotatedString(paymentReference))
                Toast.makeText(context, "Kode pembayaran disalin", Toast.LENGTH_SHORT).show()
            }) {
                Icon(Icons.Default.ContentCopy, contentDescription = "Salin")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Jumlah yang harus dibayar", fontWeight = FontWeight.Bold)
        Text(
            text = "Rp $amount",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

//        if (status.lowercase() == "pending") {
            Button(
                onClick = onRefreshStatus,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Refresh Status Pembayaran")
            }
//        }
    }
}


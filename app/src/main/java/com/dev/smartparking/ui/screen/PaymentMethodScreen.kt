package com.dev.smartparking.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.PlaylistAddCheck
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.dev.smartparking.R
import com.dev.smartparking.domain.model.PaymentMethod

//val paymentMethods = listOf(
//    PaymentMethod(
//        key = "midtrans",
//        name = "Midtrans",
//        status = true,
//        iconRes = R.drawable.midtrans
//    ),
//    PaymentMethod(key = "ovo", name = "OVO", status = true, iconRes = R.drawable.ovo),
////    PaymentMethod("gopay", "GoPay", R.drawable.ic_gopay),
////    PaymentMethod("bca", "BCA Transfer", R.drawable.ic_bca),
////    PaymentMethod("mandiri", "Mandiri Virtual Account", R.drawable.ic_mandiri),
//)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentMethodBottomSheet(
    paymentMethods: List<PaymentMethod>,
    selectedKey: Int?,
    onSelected: (PaymentMethod) -> Unit,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Pilih Metode Pembayaran",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            paymentMethods.forEach { method ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSelected(method) }
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.midtrans),
                        contentDescription = "",
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = method.methodName,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = if (method.id == selectedKey) FontWeight.Bold else FontWeight.Normal
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    if (method.id == selectedKey) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Dipilih",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
}


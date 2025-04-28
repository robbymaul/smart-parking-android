package com.dev.smartparking.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.FileDownload
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun DialogComponent(
    open: Boolean,
    onClose: () -> Unit,
    title: String? = null,
    description: String? = null,
    message: String? = null,
    variant: DialogVariant = DialogVariant.INFO,
    showCloseIcon: Boolean = true,
    actions: List<DialogAction> = emptyList()
) {
    if (!open) return

    Dialog(onDismissRequest = { onClose() }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White,
            tonalElevation = 8.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // Close Icon (top right)
                if (showCloseIcon) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        IconButton(onClick = onClose) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close",
                                tint = Color.Gray
                            )
                        }
                    }
                }

                // Variant Icon
                Icon(
                    imageVector = variant.icon,
                    contentDescription = null,
                    tint = variant.color,
                    modifier = Modifier
                        .size(80.dp)
                        .padding(top = 16.dp)
                )

                // Title
                title?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                // Description
                description?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                // Message
                message?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 12.dp)
                    )
                }

                // Actions
                if (actions.isNotEmpty()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        actions.forEach {
                            Button(
                                onClick = it.onClick,
                                modifier = Modifier.weight(1f),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = it.color()
                                )
                            ) {
                                Text(it.label)
                            }
                        }
                    }
                }
            }
        }
    }
}

enum class DialogVariant(val icon: ImageVector, val color: Color) {
    ERROR(Icons.Default.Error, Color.Red),
    SUCCESS(Icons.Default.CheckCircle, Color(0xFF2196F3)),
    WARNING(Icons.Default.Warning, Color(0xFFFF9800)),
    INFO(Icons.Default.Info, Color(0xFF878D91)),
    DELETE(Icons.Default.Delete, Color.Red),
    EXPORT_CSV(Icons.Default.FileDownload, Color(0xFF00BCD4))
}

data class DialogAction(
    val label: String,
    val onClick: () -> Unit,
    val color: @Composable () -> Color = { MaterialTheme.colorScheme.primary }
)


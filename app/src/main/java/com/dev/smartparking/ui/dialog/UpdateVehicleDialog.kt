package com.dev.smartparking.ui.dialog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.dev.smartparking.R
import com.dev.smartparking.domain.model.VehicleModel
import com.dev.smartparking.ui.element.FormTextFieldElement
import com.dev.smartparking.ui.section.SectionFormField
import com.dev.smartparking.ui.theme.SmartParkingTheme


@Composable
fun UpdateVehicleDialog(
    vehicleData: VehicleModel?,
    onDismiss: () -> Unit,
    onSave: (VehicleModel) -> Unit
) {
    var color by remember { mutableStateOf(vehicleData?.color ?: "") }
    var stnkNumber by remember { mutableStateOf(vehicleData?.licensePlate ?: "") }
    var brand by remember { mutableStateOf(vehicleData?.brand ?: "") }
    var vehicleType by remember { mutableStateOf(vehicleData?.vehicleType ?: "") }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = {
                onSave(
                    VehicleModel(
                        id = vehicleData?.id ?: 0,
                        licensePlate = stnkNumber,
                        vehicleType = vehicleType,
                        brand = brand,
                        model = vehicleData?.model ?: "",
                        color = color,
                        rfidTag = vehicleData?.rfidTag ?: "",
                        length = vehicleData?.length ?: 0.0,
                        width = vehicleData?.width ?: 0.0,
                        height = vehicleData?.height ?: 0.0,
                        isActive = vehicleData?.isActive ?: true,
                    )
                )
            }) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        },
        title = { Text("Update Vehicle Info") },
        text = {
            Column {
                OutlinedTextField(
                    value = color,
                    onValueChange = { color = it },
                    label = { Text("Color") }
                )
                OutlinedTextField(
                    value = stnkNumber,
                    onValueChange = { stnkNumber = it },
                    label = { Text("STNK Number") }
                )
                OutlinedTextField(
                    value = brand,
                    onValueChange = { brand = it },
                    label = { Text("Brand") }
                )
                OutlinedTextField(
                    value = vehicleType,
                    onValueChange = { vehicleType = it },
                    label = { Text("Type") },
                    enabled = false
                )
            }
        }
    )
}

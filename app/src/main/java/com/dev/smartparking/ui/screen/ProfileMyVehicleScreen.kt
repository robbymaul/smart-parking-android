package com.dev.smartparking.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.dev.smartparking.R
import com.dev.smartparking.data.local.datastore.AuthPreferences
import com.dev.smartparking.domain.model.UserModel
import com.dev.smartparking.ui.component.ButtonComponent
import com.dev.smartparking.ui.component.DialogComponent
import com.dev.smartparking.ui.component.DialogVariant
import com.dev.smartparking.ui.dialog.UpdateVehicleDialog
import com.dev.smartparking.ui.section.ContentSection
import com.dev.smartparking.viewmodel.ProfileViewModel
import kotlinx.coroutines.flow.first
import org.koin.compose.koinInject

@Composable()
fun ProfileMyVehicleScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    profileViewModel: ProfileViewModel
) {
    var showDialogUpdateVehicle by remember { mutableStateOf(false) }
    val authPreferences = koinInject<AuthPreferences>()

    val user = produceState<UserModel?>(initialValue = null) {
        value = authPreferences.user.first()
    }

    LaunchedEffect(Unit) {
        profileViewModel.getUserVehicle {
            navController.popBackStack()
        }
    }

    Column(
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .clip(RoundedCornerShape(16.dp))
                .shadow(4.dp, RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surface)
                .heightIn(min = 240.dp, max = 240.dp), // Supaya tidak terlalu tinggi statis
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    painter = painterResource(R.drawable.yellow_car),
                    contentDescription = "Vehicle Image",
                    modifier = Modifier
                        .size(140.dp)
                )

                Text(
                    text = "${profileViewModel.userVehiclesModel?.brand} ${profileViewModel.userVehiclesModel?.model}",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    ),
                    modifier = Modifier.align(Alignment.Start)
                )

                Text(
                    text = "${profileViewModel.userVehiclesModel?.licensePlate}",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = LocalContentColor.current.copy(alpha = 0.7f)
                    ),
                    modifier = Modifier.align(Alignment.Start)
                )

                Text(
                    text = if (user.value?.isVehicleActivated == true) "Verified" else "Unverified",
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = Color(0xFF4CAF50), // Hijau untuk status
                        fontSize = 12.sp
                    ),
                    modifier = Modifier.align(Alignment.Start)
                )
            }
        }

        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .clip(RoundedCornerShape(16.dp))
                .shadow(4.dp, RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surface)
                .heightIn(min = 320.dp, max = 320.dp), // Supaya tidak terlalu tinggi statis
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 8.dp) // Padding dalam Card
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    ContentSection(
                        title = R.string.txt_title_name_of_owner1,
                        modifier = Modifier.weight(2f)
                    ) {
                        Text(
                            text = "${profileViewModel.userProfileModel?.firstName} ${profileViewModel.userProfileModel?.lastName}",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = LocalContentColor.current.copy(alpha = 0.7f),
                                textAlign = TextAlign.Start
                            ),
                        )
                    }
                    ContentSection(
                        title = R.string.txt_title_color,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "${profileViewModel.userVehiclesModel?.color}",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = LocalContentColor.current.copy(alpha = 0.7f),
                                textAlign = TextAlign.Start
                            )
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    ContentSection(
                        title = R.string.txt_title_stnk,
                        modifier = Modifier.weight(2f)
                    ) {
                        Text(
                            text = "${profileViewModel.userVehiclesModel?.licensePlate}",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = LocalContentColor.current.copy(alpha = 0.7f),
                                textAlign = TextAlign.Start
                            ),
                        )
                    }
                    ContentSection(
                        title = R.string.txt_title_brand,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "${profileViewModel.userVehiclesModel?.brand}",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = LocalContentColor.current.copy(alpha = 0.7f),
                                textAlign = TextAlign.Start
                            ),
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    ContentSection(
                        title = R.string.txt_title_vehicle_type,
                        modifier = Modifier.weight(2f)
                    ) {
                        Text(
                            text = "${profileViewModel.userVehiclesModel?.vehicleType}",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = LocalContentColor.current.copy(alpha = 0.7f),
                                textAlign = TextAlign.Start
                            ),
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                ButtonComponent(
                    text = R.string.txt_button_update,
                    textColor = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    // TODO: Book Now Action
                    showDialogUpdateVehicle = true
                }
            }
        }
    }

    if (showDialogUpdateVehicle) {
        UpdateVehicleDialog(
            vehicleData = profileViewModel.userVehiclesModel ?: null,
            onDismiss = { showDialogUpdateVehicle = false },
            onSave = { updatedData ->
                // Lakukan update ke ViewModel atau backend
                profileViewModel.updateVehicles(updatedData) {
                    showDialogUpdateVehicle = false
                }
            }
        )
    }

    DialogComponent(
        open = profileViewModel.isUpdateVehicleSuccess,
        onClose = {
            profileViewModel.onIsUpdateVehicleSuccess(false)
        },
        title = "Update Vehicle",
        description = "Update Berhasil",
        variant = DialogVariant.SUCCESS,
    )

    DialogComponent(
        open = profileViewModel.isUpdateVehicleFailed,
        onClose = {
            profileViewModel.onIsUpdateVehicleFailed(false)
        },
        title = "Update Vehicle",
        description = "Update Gagal",
        variant = DialogVariant.ERROR,
    )
}
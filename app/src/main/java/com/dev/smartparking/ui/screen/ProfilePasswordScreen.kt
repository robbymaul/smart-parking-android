package com.dev.smartparking.ui.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.dev.smartparking.R
import com.dev.smartparking.data.local.datastore.AuthPreferences
import com.dev.smartparking.domain.model.UserModel
import com.dev.smartparking.route.Screen
import com.dev.smartparking.ui.component.ButtonComponent
import com.dev.smartparking.ui.component.DialogComponent
import com.dev.smartparking.ui.component.DialogVariant
import com.dev.smartparking.ui.component.LoadingButton
import com.dev.smartparking.ui.dialog.UpdateVehicleDialog
import com.dev.smartparking.ui.element.FormTextFieldElement
import com.dev.smartparking.ui.section.IntroSection
import com.dev.smartparking.ui.section.SectionFormField
import com.dev.smartparking.viewmodel.ProfileViewModel
import kotlinx.coroutines.flow.first
import org.koin.compose.koinInject

@Composable()
fun ProfilePasswordScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    profileViewModel: ProfileViewModel
) {
    var oldPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmNewPassword by remember { mutableStateOf("") }


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
                .fillMaxHeight()
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
                    painter = painterResource(R.drawable.image_reset_password1),
                    contentDescription = stringResource(R.string.title_screen_set_new_password),
                    modifier = Modifier
                        .width(94.dp)
                        .height(94.dp)
                )

                IntroSection(
                    title = stringResource(R.string.title_screen_set_new_password) ,
                    description = stringResource(R.string.desc_screen_set_new_password) ,
                )

                SectionFormField(
                    title = stringResource(R.string.txt_title_field_old_password1),
                    textStyle = MaterialTheme.typography.titleMedium
                ) {
                    var passwordVisible by remember { mutableStateOf(false) }

                    FormTextFieldElement(
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        placeHolder = stringResource(R.string.txt_title_field_old_password1),
                        value = oldPassword,
                        onValueChange = { value -> oldPassword = value },
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            val image =
                                if (passwordVisible) Icons.Default.Visibility else Icons.Filled.VisibilityOff
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(
                                    imageVector = image,
                                    contentDescription = stringResource(R.string.txt_place_holder_form_password)
                                )
                            }
                        }
                    )
                }

                SectionFormField(
                    title = stringResource(R.string.txt_title_field_password1),
                    textStyle = MaterialTheme.typography.titleMedium
                ) {
                    var passwordVisible by remember { mutableStateOf(false) }

                    FormTextFieldElement(
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        placeHolder = stringResource(R.string.txt_title_field_password1),
                        value = newPassword,
                        onValueChange = { value -> newPassword = value },
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            val image =
                                if (passwordVisible) Icons.Default.Visibility else Icons.Filled.VisibilityOff
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(
                                    imageVector = image,
                                    contentDescription = stringResource(R.string.txt_place_holder_form_password)
                                )
                            }
                        }
                    )
                }

                SectionFormField(
                    title = stringResource(R.string.txt_title_field_confirm_password),
                    textStyle = MaterialTheme.typography.titleMedium
                ) {
                    var passwordVisible by remember { mutableStateOf(false) }

                    FormTextFieldElement(
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        placeHolder = stringResource(R.string.txt_title_field_confirm_password),
                        value = confirmNewPassword,
                        onValueChange = { value -> confirmNewPassword = value },
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            val image =
                                if (passwordVisible) Icons.Default.Visibility else Icons.Filled.VisibilityOff
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(
                                    imageVector = image,
                                    contentDescription = stringResource(R.string.txt_place_holder_form_password)
                                )
                            }
                        }
                    )
                }

                LoadingButton(
                    text = R.string.txt_button_submit,
                    isLoading = profileViewModel.isLoading
                ) {
                    profileViewModel.updateUserPassword(oldPassword, newPassword, confirmNewPassword) {
                        navController.popBackStack()
                        profileViewModel.onisUpdateUserPasswordSuccess(false)
                    }
                }

//                ButtonComponent(
//                    text = R.string.txt_button_submit,
//                    textColor = Color.White
//                ) {
//                    profileViewModel.updateUserPassword(oldPassword, newPassword, confirmNewPassword) {
//                        navController.popBackStack()
//                        profileViewModel.onisUpdateUserPasswordSuccess(false)
//                    }
//                }
            }
        }
    }

    //        if (showDialogUpdateVehicle) {
//            UpdateVehicleDialog(
//                vehicleData = profileViewModel.userVehiclesModel ?: null,
//                onDismiss = { showDialogUpdateVehicle = false },
//                onSave = { updatedData ->
//                    // Lakukan update ke ViewModel atau backend
//                    profileViewModel.updateVehicles(updatedData) {
//                        showDialogUpdateVehicle = false
//                    }
//                }
//            )
//        }

            DialogComponent(
                open = profileViewModel.isUpdateUserPasswordSuccess,
                onClose = {
                    navController.popBackStack()
                    profileViewModel.onisUpdateUserPasswordSuccess(false)
                },
                title = "Ubah Password",
            description = profileViewModel.updatePasswordMessage,
                variant = DialogVariant.SUCCESS,
            )

            DialogComponent(
                open = profileViewModel.isUpdateUserPasswordFailed,
                onClose = {
                    profileViewModel.onIsUpdateUserPasswordFailed(false)
                },
                title = "Ubah Password",
                description = profileViewModel.errorMessage,
                variant = DialogVariant.ERROR,
            )
}
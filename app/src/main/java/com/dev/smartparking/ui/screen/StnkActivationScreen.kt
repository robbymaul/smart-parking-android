package com.dev.smartparking.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocalPolice
import androidx.compose.material.icons.outlined.TypeSpecimen
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dev.smartparking.R
import com.dev.smartparking.route.Screen
import com.dev.smartparking.ui.component.ButtonComponent
import com.dev.smartparking.ui.component.DialogAction
import com.dev.smartparking.ui.component.DialogComponent
import com.dev.smartparking.ui.component.DialogVariant
import com.dev.smartparking.ui.component.ImageStnkActivationComponent
import com.dev.smartparking.ui.component.LoadingButton
import com.dev.smartparking.ui.element.FormTextFieldElement
import com.dev.smartparking.ui.section.IntroSection
import com.dev.smartparking.ui.section.SectionFormField
import com.dev.smartparking.ui.theme.SmartParkingTheme
import com.dev.smartparking.viewmodel.STNKActivationViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun StnkActivationScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    stnkActivationViewModel: STNKActivationViewModel,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        IntroSection(
            title = stringResource(R.string.title_screen_activation),
            description = stringResource(R.string.desc_screen_activation)
        )
        ImageStnkActivationComponent()
        SectionFormField(
            title = stringResource(R.string.txt_place_holder_vehicle_number),
            textStyle = MaterialTheme.typography.titleMedium
        ) {
            FormTextFieldElement(
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    capitalization = KeyboardCapitalization.Characters
                ),
                placeHolder = stringResource(R.string.txt_place_holder_vehicle_number),
                value = stnkActivationViewModel.licensePlate,
                onValueChange = { value -> stnkActivationViewModel.onLicensePlateChange(value.uppercase()) },
                visualTransformation = VisualTransformation.None,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.LocalPolice,
                        contentDescription = stringResource(R.string.txt_place_holder_vehicle_number)
                    )
                }
            )
        }
//        if (!isValid) {
//            Text(text = "Ga Valid Tuh")
//        }

//        SectionFormField(
//            title = stringResource(R.string.txt_place_holder_vehicle_type),
//            textStyle = MaterialTheme.typography.titleMedium
//        ) {
//            FormTextFieldElement(
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//                placeHolder = stringResource(R.string.txt_place_holder_vehicle_type),
//                value = stnkActivationViewModel.licensePlate,
//                onValueChange = {value -> stnkActivationViewModel.onLicensePlateChange(value)},
//                visualTransformation =  VisualTransformation.None,
//                trailingIcon = {
//                    Icon(
//                        imageVector = Icons.Outlined.TypeSpecimen,
//                        contentDescription = stringResource(R.string.txt_place_holder_vehicle_type)
//                    )
//                }
//            )
//        }

        LoadingButton(
            text = R.string.txt_button_submit,
            onClick = {
                stnkActivationViewModel.createVehicle {
                    stnkActivationViewModel.viewModelScope.launch {
                        delay(1000)
                    }

                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.Activation.route) { inclusive = true }
                    }
                }
            },
            isLoading = stnkActivationViewModel.isLoading
        )
    }

    DialogComponent(
        open = stnkActivationViewModel.isCreateVehicleSuccessful,
        onClose = {
            stnkActivationViewModel.isCreateVehicleSuccessfulChange(false)
        },
        title = "Vehicle Activation",
        description = "Berhasil",
        variant = DialogVariant.SUCCESS,
    )

    DialogComponent(
        open = stnkActivationViewModel.isCreateVehicleFailed,
        onClose = {
            stnkActivationViewModel.isCreateVehicleFailedChange(false)
        },
        title = "Vehicle Activation",
        description = stnkActivationViewModel.errorMessage,
        variant = DialogVariant.ERROR,
        actions = listOf(
            DialogAction(label = "Tutup", onClick = {
                stnkActivationViewModel.isCreateVehicleFailedChange(false)
            }),
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun StnkActivationScreenPreview() {
    SmartParkingTheme {
        StnkActivationScreen(
            navController = rememberNavController(),
            stnkActivationViewModel = koinViewModel(),
        )
    }
}
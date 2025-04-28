package com.dev.smartparking.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocalPolice
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dev.smartparking.R
import com.dev.smartparking.route.Screen
import com.dev.smartparking.ui.component.DialogAction
import com.dev.smartparking.ui.component.DialogComponent
import com.dev.smartparking.ui.component.DialogVariant
import com.dev.smartparking.ui.component.LoadingButton
import com.dev.smartparking.ui.element.FormTextFieldElement
import com.dev.smartparking.ui.section.IntroSection
import com.dev.smartparking.ui.section.SectionFormField
import com.dev.smartparking.ui.theme.SmartParkingTheme
import com.dev.smartparking.viewmodel.ForgotPasswordViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun ForgotPasswordScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    forgotPasswordViewModel: ForgotPasswordViewModel
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
            title = stringResource(R.string.title_screen_forgot_password),
            description = stringResource(R.string.desc_screen_forgot_password)
        )
        Image(
            painter = painterResource(id = R.drawable.image_forgot_password1),
            contentDescription = stringResource(R.string.title_screen_forgot_password),
            modifier = Modifier.fillMaxWidth()
        )
        SectionFormField(
            title = stringResource(R.string.txt_title_field_phone_number),
            textStyle = MaterialTheme.typography.titleMedium
        ) {
            FormTextFieldElement(
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                placeHolder = stringResource(R.string.txt_place_holder_form_phone_number),
                value = forgotPasswordViewModel.phoneNumber,
                onValueChange = {value -> forgotPasswordViewModel.onPhoneNumberChange(value)},
                visualTransformation = VisualTransformation.None,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.LocalPolice,
                        contentDescription = stringResource(R.string.txt_title_field_phone_number1)
                    )
                },
                prefix = { Text("+62") }
            )
        }
        Row {
            Text(
                text = stringResource(R.string.desc_field_forgot_password_phone_number)
            )
        }

        LoadingButton(
            text = R.string.txt_button_submit,
            onClick = {
                forgotPasswordViewModel.forgotPassword {
                    forgotPasswordViewModel.isForgotPasswordSuccessfulChange(true)
                    forgotPasswordViewModel.viewModelScope.launch {
                        delay(1000)
                    }
                    navController.navigate(Screen.ForgotPasswordOTP.route)
                }
            },
            isLoading = forgotPasswordViewModel.isLoading
        )

//        ButtonComponent(
//            text = R.string.txt_button_submit,
//            textColor = MaterialTheme.colorScheme.background,
//            onClick = {
//                navController.navigate(Screen.ForgotPasswordOTP.route)
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(
//                    start = 8.dp,
//                    end = 8.dp,
//                    top = 16.dp,
//                )
//                .height(42.dp)
//        )
    }

    DialogComponent(
        open = forgotPasswordViewModel.isForgotPasswordSuccessful,
        onClose = {
            forgotPasswordViewModel.isForgotPasswordSuccessfulChange(false)
        },
        title = "Forgot Password",
        description = "Berhasil",
        variant = DialogVariant.SUCCESS,
    )

    DialogComponent(
        open = forgotPasswordViewModel.isForgotPasswordFailed,
        onClose = {
            forgotPasswordViewModel.isForgotPasswordFailedChange(false)
        },
        title = "Forgot Password",
        description = forgotPasswordViewModel.errorMessage,
        variant = DialogVariant.ERROR,
        actions = listOf(
            DialogAction(label = "Tutup", onClick = {
                forgotPasswordViewModel.isForgotPasswordFailedChange(false)
            }),
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun ForgotPasswordScreenPreview() {
    SmartParkingTheme {
        ForgotPasswordScreen(navController = rememberNavController(), forgotPasswordViewModel = koinViewModel())
    }
}
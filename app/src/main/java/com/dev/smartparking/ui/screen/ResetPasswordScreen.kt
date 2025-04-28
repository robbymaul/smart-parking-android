package com.dev.smartparking.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dev.smartparking.R
import com.dev.smartparking.route.Screen
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
fun ResetPasswordScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    forgotPasswordViewModel: ForgotPasswordViewModel,
    mainNavController: NavHostController
) {
    Column (
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
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
            title =  stringResource(R.string.txt_title_field_password1),
            textStyle = MaterialTheme.typography.titleMedium
        ) {
            var passwordVisible by remember { mutableStateOf(false) }

            FormTextFieldElement(
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                placeHolder = stringResource(R.string.txt_place_holder_form_password),
                value = forgotPasswordViewModel.password,
                onValueChange = {value -> forgotPasswordViewModel.onPasswordChange(value)},
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible) Icons.Default.Visibility else Icons.Filled.VisibilityOff
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = image,
                            contentDescription = stringResource(R.string.txt_title_field_password1)
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
                placeHolder = stringResource(R.string.txt_place_holder_form_password),
                value = forgotPasswordViewModel.confirmPassword,
                onValueChange = {value -> forgotPasswordViewModel.onConfirmPasswordChange(value)},
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible) Icons.Default.Visibility else Icons.Filled.VisibilityOff
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = image,
                            contentDescription = stringResource(R.string.txt_title_field_password1)
                        )
                    }
                }
            )
        }

        LoadingButton(
            text = R.string.txt_button_verify_otp,
            onClick = {
                forgotPasswordViewModel.resetPassword {
                    forgotPasswordViewModel.viewModelScope.launch {
                        delay(1000)
                    }

                    navController.popBackStack(navController.graph.startDestinationId, inclusive = true)

                    mainNavController.navigate(Screen.Login.route) {
                        popUpTo(0)
                    }

                    forgotPasswordViewModel.viewModelScope.launch {
                        delay(1000)
                    }
                    forgotPasswordViewModel.isResetPasswordSuccessfulChange(false)
                }
            },

            isLoading = forgotPasswordViewModel.isLoading
        )
    }

    DialogComponent(
        open = forgotPasswordViewModel.isResetPasswordSuccessful,
        onClose = {
            forgotPasswordViewModel.isResetPasswordSuccessfulChange(false)
        },
        title = "Reset Password",
        description = "Berhasil",
        variant = DialogVariant.SUCCESS,
    )

    DialogComponent(
        open = forgotPasswordViewModel.isResetPasswordFailed,
        onClose = {
            forgotPasswordViewModel.isResetPasswordFailedChange(false)
        },
        title = "Reset Password",
        description = forgotPasswordViewModel.errorMessage,
        variant = DialogVariant.ERROR,
    )
}

@Preview(showBackground = true)
@Composable
private fun ResetPasswordScreenPreview() {
    SmartParkingTheme {
        ResetPasswordScreen(
            navController =  rememberNavController(),
            forgotPasswordViewModel = koinViewModel(),
            mainNavController = rememberNavController()
        )
    }
}
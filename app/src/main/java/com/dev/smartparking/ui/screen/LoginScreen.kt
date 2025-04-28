package com.dev.smartparking.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.PhoneAndroid
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.dev.smartparking.ui.component.DialogAction
import com.dev.smartparking.ui.component.DialogComponent
import com.dev.smartparking.ui.component.DialogVariant
import com.dev.smartparking.ui.component.LoadingButton
import com.dev.smartparking.ui.component.SocialLoginButton
import com.dev.smartparking.ui.element.FormTextFieldElement
import com.dev.smartparking.ui.element.LoadingDialog
import com.dev.smartparking.ui.section.IntroSection
import com.dev.smartparking.ui.section.SectionFormField
import com.dev.smartparking.ui.theme.SmartParkingTheme
import com.dev.smartparking.viewmodel.LoginViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    loginViewModel: LoginViewModel
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(R.drawable.smart_parking_logo1),
            contentDescription = stringResource(R.string.title_screen_login),
            modifier = Modifier
                .width(94.dp)
                .height(94.dp)
        )
        IntroSection(
            title = stringResource(R.string.title_screen_login),
            description = stringResource(R.string.desc_screen_login),
        )
        SectionFormField(
            title = stringResource(R.string.txt_title_field_phone_number1),
            textStyle = MaterialTheme.typography.titleMedium

        ) {
            SmartParkingTheme(dynamicColor = false) {
                FormTextFieldElement(
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    placeHolder = stringResource(R.string.txt_place_holder_form_phone_number),
                    value = loginViewModel.phoneNumber,
                    onValueChange = { value -> loginViewModel.onPhoneNumberChange(value) },
                    visualTransformation = VisualTransformation.None,
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.PhoneAndroid,
                            contentDescription = stringResource(R.string.txt_title_field_phone_number1)
                        )
                    },
                    prefix = { Text("+62") }
                )
            }
        }
        SectionFormField(
            title = stringResource(R.string.txt_title_field_password1),
            textStyle = MaterialTheme.typography.titleMedium
        ) {
            var passwordVisible by remember { mutableStateOf(false) }

            FormTextFieldElement(
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                placeHolder = stringResource(R.string.txt_place_holder_form_password),
                value = loginViewModel.password,
                onValueChange = { value -> loginViewModel.onPasswordChange(value) },
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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(
                onClick = {
                    navController.navigate(Screen.ForgotPassword.route)
                },
            ) {
                Text(
                    text = stringResource(R.string.txt_forgot_password1)
                )
            }
        }


        LoadingButton(
            text = R.string.txt_button_login1,
            onClick = {
                loginViewModel.login() {
                    loginViewModel.onIsLoginSuccessfulChange(true)
                    loginViewModel.viewModelScope.launch {
                        delay(300)
                    }
                    navController.navigate(Screen.OTP.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            },
            isLoading = loginViewModel.isLoading
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.txt_not_register1)
            )
            TextButton(
                onClick = {
                    navController.navigate(Screen.Register.route)
                },
            ) {
                Text(
                    text = stringResource(R.string.txt_create_account1)
                )
            }
        }

        Text(
            text = stringResource(R.string.txt_or_signup1),
            modifier = Modifier.paddingFromBaseline(
                top = 32.dp,
                bottom = 8.dp
            )
        )
        SocialLoginButton()
    }

    LoadingDialog(loginViewModel.isLoading)

    DialogComponent(
        open = loginViewModel.isLoginSuccessful,
        onClose = {
            loginViewModel.onIsLoginSuccessfulChange(false)
        },
        title = "Masuk",
        description = "Berhasil Masuk",
        variant = DialogVariant.SUCCESS,
    )

    DialogComponent(
        open = loginViewModel.isLoginFailed,
        onClose = {
            loginViewModel.onIsLoginFailedChange(false)
        },
        title = "Masuk",
        description = loginViewModel.errorMessage,
        variant = DialogVariant.ERROR,
        actions = listOf(
            DialogAction(label = "Tutup", onClick = {
                loginViewModel.onIsLoginFailedChange(false)
            }),
        )
    )

}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    SmartParkingTheme {
        LoginScreen(navController = rememberNavController(), loginViewModel = koinViewModel())
    }
}
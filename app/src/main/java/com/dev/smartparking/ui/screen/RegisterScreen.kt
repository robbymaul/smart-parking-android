package com.dev.smartparking.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.outlined.Person
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
import com.dev.smartparking.ui.element.LoadingDialog
import com.dev.smartparking.ui.section.IntroSection
import com.dev.smartparking.ui.section.SectionFormField
import com.dev.smartparking.ui.theme.SmartParkingTheme
import com.dev.smartparking.viewmodel.RegisterViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    registerViewModel: RegisterViewModel
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
            contentDescription = stringResource(R.string.title_screen_register),
            modifier = Modifier
                .width(94.dp)
                .height(94.dp)
        )
        IntroSection(
            title = stringResource(R.string.title_screen_register),
            description = stringResource(R.string.desc_screen_register)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp) // biar ada jarak antar field
        ) {
            SectionFormField(
                modifier = Modifier.weight(1f),
                title = stringResource(R.string.txt_title_field_firstname1),
                textStyle = MaterialTheme.typography.titleMedium
            ) {
                SmartParkingTheme(dynamicColor = false) {
                    FormTextFieldElement(
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        placeHolder = stringResource(R.string.txt_title_field_firstname1),
                        value = registerViewModel.firstName,
                        onValueChange = { value ->
                            registerViewModel.onFirstNameChange(value = value)
                        },
                        visualTransformation = VisualTransformation.None,
                        trailingIcon = {
                            Box(modifier = Modifier.padding(end = 8.dp)) {
                                Icon(
                                    imageVector = Icons.Outlined.Person,
                                    contentDescription = stringResource(R.string.txt_title_field_firstname1)
                                )
                            }
                        }
                    )
                }
            }

            SectionFormField(
                modifier = Modifier.weight(1f),
                title = stringResource(R.string.txt_title_field_lastname1),
                textStyle = MaterialTheme.typography.titleMedium
            ) {
                SmartParkingTheme(dynamicColor = false) {
                    FormTextFieldElement(
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        placeHolder = stringResource(R.string.txt_title_field_lastname1),
                        value = registerViewModel.lastName,
                        onValueChange = { value ->
                            registerViewModel.onLastNameChange(value = value)
                        },
                        visualTransformation = VisualTransformation.None,
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Person,
                                contentDescription = stringResource(R.string.txt_title_field_lastname1)
                            )
                        }
                    )
                }
            }
        }

        SectionFormField(
            title = stringResource(R.string.txt_title_field_email1),
            textStyle = MaterialTheme.typography.titleMedium

        ) {
            SmartParkingTheme(dynamicColor = false) {
                FormTextFieldElement(
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    placeHolder = stringResource(R.string.txt_title_field_email1),
                    value = registerViewModel.email,
                    onValueChange = { value ->
                        registerViewModel.onEmailChange(value = value)
                    },
                    visualTransformation = VisualTransformation.None,
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Person,
                            contentDescription = stringResource(R.string.txt_title_field_email1)
                        )
                    }
                )
            }
        }

        SectionFormField(
            title = stringResource(R.string.txt_title_field_phone_number1),
            textStyle = MaterialTheme.typography.titleMedium

        ) {
            SmartParkingTheme(dynamicColor = false) {
                FormTextFieldElement(
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    placeHolder = stringResource(R.string.txt_place_holder_form_phone_number),
                    value = registerViewModel.phoneNumber,
                    onValueChange = { value -> registerViewModel.onPhoneNumberChange(value = value) },
                    visualTransformation = VisualTransformation.None,
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.PhoneAndroid,
                            contentDescription = stringResource(R.string.txt_place_holder_form_phone_number)
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
                value = registerViewModel.password,
                onValueChange = { value -> registerViewModel.onPasswordChange(value = value) },
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

        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .paddingFromBaseline(bottom = 12.dp),
            text = stringResource(R.string.desc_screen_set_new_password),
            style = TextStyle(
                fontSize = 12.sp,
                textAlign = TextAlign.Justify,
                color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.9f)
            )
        )
        LoadingButton(
            text = R.string.txt_button_register,
            onClick = { registerViewModel.register() {
                registerViewModel.onIsRegisterSuccessfulChange(true)
                registerViewModel.viewModelScope.launch {
                    delay(300)
                }
                navController.navigate(Screen.Login.route) {
                    popUpTo(Screen.Register.route) { inclusive = true }
                }
            } },
            isLoading = registerViewModel.isLoading
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.txt_have_and_account),
                fontWeight = FontWeight.SemiBold
            )
            TextButton(
                onClick = {
                    navController?.navigate(Screen.Login.route)
                },
            ) {
                Text(
                    text = stringResource(R.string.txt_button_login1)
                )
            }
        }
    }

    LoadingDialog(registerViewModel.isLoading)

    DialogComponent(
        open = registerViewModel.isRegistrationSuccessful,
        onClose = {
            registerViewModel.onIsRegisterSuccessfulChange(false)
        },
        title = "Daftar",
        description = "Pendaftaran Berhasil",
        variant = DialogVariant.SUCCESS,
//        actions = listOf(
//            DialogAction(label = "Batal", onClick = { }),
//            DialogAction(label = "Hapus", onClick = {
//                // TODO: Hapus data
////                showDialog = false
//            }, color = { Color.Red })
//        )
    )

    DialogComponent(
        open = registerViewModel.isRegistrationFailed,
        onClose = {
            registerViewModel.onIsRegisterFailedChange(false)
        },
        title = "Daftar",
        description = registerViewModel.errorMessage,
        variant = DialogVariant.ERROR,
        actions = listOf(
            DialogAction(label = "Tutup", onClick = {
                registerViewModel.onIsRegisterFailedChange(false)
            }),
//            DialogAction(label = "Hapus", onClick = {
//                // TODO: Hapus data
////                showDialog = false
//            }, color = { Color.Red })
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun RegisterScreenPreview() {
    SmartParkingTheme {
        RegisterScreen(
            navController = rememberNavController(),
            registerViewModel = koinViewModel()
        )
    }
}
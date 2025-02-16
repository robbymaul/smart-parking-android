package com.dev.smartparking.ui.screen

import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.PhoneCallback
import androidx.compose.material.icons.automirrored.filled.PhoneForwarded
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
import com.dev.smartparking.R
import com.dev.smartparking.ui.component.ButtonComponent
import com.dev.smartparking.ui.component.ImageButtonComponent
import com.dev.smartparking.ui.component.SocialLoginButton
import com.dev.smartparking.ui.element.FormTextFieldElement
import com.dev.smartparking.ui.section.IntroSection
import com.dev.smartparking.ui.section.SectionFormField
import com.dev.smartparking.ui.theme.SmartParkingTheme

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    Column (
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
            title = R.string.title_screen_login,
            description = R.string.desc_screen_login,
        )
        SectionFormField(
            title = R.string.txt_title_field_phone_number1,
            textStyle = MaterialTheme.typography.titleMedium

        ) {
            SmartParkingTheme (dynamicColor = false) {
                FormTextFieldElement(
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    placeHolder = R.string.txt_place_holder_form_phone_number,
                    value = "",
                    onValueChange = {},
                    visualTransformation =  VisualTransformation.None,
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.PhoneAndroid,
                            contentDescription = stringResource(R.string.txt_title_field_phone_number1)
                        )
                    }
                )
            }
        }
        SectionFormField(
            title = R.string.txt_title_field_password1,
            textStyle = MaterialTheme.typography.titleMedium
        ) {
            var passwordVisible by remember { mutableStateOf(false) }

            FormTextFieldElement(
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                placeHolder = R.string.txt_place_holder_form_password,
                value = "",
                onValueChange = {},
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
        ButtonComponent(
            text = R.string.txt_button_login1,
            textColor = MaterialTheme.colorScheme.background,
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 8.dp,
                    end = 8.dp,
                    top = 16.dp,
                )
                .height(42.dp)
        )
        Row (
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.txt_not_register1)
            )
            TextButton(
                onClick = {},
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
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    SmartParkingTheme {
        LoginScreen()
    }
}
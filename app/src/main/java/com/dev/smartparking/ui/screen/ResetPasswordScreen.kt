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
import androidx.compose.ui.draw.clip
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
fun ResetPasswordScreen(modifier: Modifier = Modifier) {
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
            title = R.string.title_screen_set_new_password,
            description = R.string.desc_screen_set_new_password,
        )
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
        SectionFormField(
            title = R.string.txt_title_field_confirm_password,
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
            text = R.string.txt_button_submit,
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
    }
}

@Preview(showBackground = true)
@Composable
private fun ResetPasswordScreenPreview() {
    SmartParkingTheme {
        ResetPasswordScreen()
    }
}
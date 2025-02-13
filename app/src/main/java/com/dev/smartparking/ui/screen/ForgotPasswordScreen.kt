package com.dev.smartparking.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocalPolice
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.smartparking.R
import com.dev.smartparking.ui.component.ButtonComponent
import com.dev.smartparking.ui.component.ImageStnkActivationComponent
import com.dev.smartparking.ui.element.FormTextFieldElement
import com.dev.smartparking.ui.section.IntroSection
import com.dev.smartparking.ui.section.SectionFormField
import com.dev.smartparking.ui.theme.SmartParkingTheme

@Composable
fun ForgotPasswordScreen(modifier: Modifier = Modifier) {
    Column (
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        IntroSection(
            title = R.string.title_screen_forgot_password,
            description = R.string.desc_screen_forgot_password
        )
        Image(
            painter = painterResource(id = R.drawable.image_forgot_password1),
            contentDescription = stringResource(R.string.title_screen_forgot_password),
            modifier = Modifier.fillMaxWidth()
        )
        SectionFormField(
            title = R.string.txt_title_field_phone_number,
            textStyle = MaterialTheme.typography.titleMedium
        ) {
            FormTextFieldElement(
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                placeHolder = R.string.txt_place_holder_form_phone_number,
                value = "",
                onValueChange = {},
                visualTransformation =  VisualTransformation.None,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.LocalPolice,
                        contentDescription = stringResource(R.string.txt_title_field_phone_number1)
                    )
                }
            )
        }
        Row { Text(
            text = stringResource(R.string.desc_field_forgot_password_phone_number)
        ) }

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
private fun ForgotPasswordScreenPreview() {
    SmartParkingTheme {
        ForgotPasswordScreen()
    }
}
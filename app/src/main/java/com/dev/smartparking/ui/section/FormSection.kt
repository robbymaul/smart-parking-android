package com.dev.smartparking.ui.section

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.smartparking.R
import com.dev.smartparking.ui.element.FormTextFieldElement
import com.dev.smartparking.ui.theme.SmartParkingTheme
import java.util.Locale

@Composable
fun SectionFormField(
    modifier: Modifier = Modifier,
    title: String,
    textStyle: TextStyle,
    content: @Composable ()-> Unit
) {
    Column (
        modifier = modifier
    ) {
        Text(
            title,
            style = textStyle,
            modifier = Modifier
                .paddingFromBaseline(top = 32.dp, bottom = 4.dp)
                .padding(horizontal = 8.dp)
        )
        content()
    }
}

@Preview(showBackground = true)
@Composable
private fun SectionFormFieldPreview() {
    var passwordVisible by remember { mutableStateOf(false) }

    SmartParkingTheme {
        SectionFormField(
            title = stringResource(R.string.txt_title_field_password1) ,
            textStyle = MaterialTheme.typography.titleMedium
        ) {
            FormTextFieldElement(
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                placeHolder =  stringResource(R.string.txt_place_holder_form_password),
                value = "",
                onValueChange = {},
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible) Icons.Default.Visibility else Icons.Filled.VisibilityOff
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = image,
                            contentDescription = "Toggle Password Visibility"
                        )
                    }
                }
            )
        }
    }
}
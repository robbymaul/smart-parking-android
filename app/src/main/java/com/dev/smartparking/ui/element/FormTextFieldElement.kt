package com.dev.smartparking.ui.element

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.dev.smartparking.R
import com.dev.smartparking.ui.theme.SmartParkingTheme

@Composable
fun FormTextFieldElement(
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions,
    @StringRes placeHolder: Int,
    value: String,
    onValueChange: (String)-> Unit,
    visualTransformation: VisualTransformation,
    trailingIcon: (@Composable ()-> Unit)? = null
) {
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = keyboardOptions,
        placeholder = {
            Text(
                text = stringResource(placeHolder),
                style = TextStyle(
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),

                )
            )
        },
        shape = RoundedCornerShape(8.dp),
        textStyle = TextStyle(
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.primary
        ),
        visualTransformation = visualTransformation,
        trailingIcon = trailingIcon,
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun FormTextFieldElementPreview() {
    var passwordVisible by remember { mutableStateOf(false) }
    SmartParkingTheme (dynamicColor = false) {
        FormTextFieldElement(
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            placeHolder = R.string.txt_place_holder_form_password,
            value = "asdasdasd",
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
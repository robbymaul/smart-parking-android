package com.dev.smartparking.ui.element

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev.smartparking.ui.theme.SmartParkingTheme

@Composable
fun TextFieldOtpElement(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    focusManager: FocusManager,
    onBackspaceEmpty: (() -> Unit)? = null
) {
    val previousValue = remember { mutableStateOf(value) }

    LaunchedEffect(key1 = value) {
        if (value.isNotEmpty()) {
            focusManager.moveFocus(FocusDirection.Next)
        }

        previousValue.value = value
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier
                .width(48.dp) // diperbesar dari 30.dp
                .height(56.dp)
                .padding(2.dp)
                .onKeyEvent { e ->
                    if (e.type == KeyEventType.KeyDown && e.key == Key.Backspace && value.isEmpty()) {
                        onBackspaceEmpty?.invoke()
                        true
                    } else {
                        false
                    }
                },
            singleLine = true,
            textStyle = LocalTextStyle.current.copy(
                fontSize = 16.sp,
                color = Color.Black, // pastikan warnanya terlihat
                textAlign = TextAlign.Center
            ),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                cursorColor = Color.Black
            ),
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.NumberPassword
            ),
        )
        Divider(
            modifier = Modifier
                .width(24.dp)
                .padding(top = 2.dp)
                .offset(y = (-10).dp),
            color = Color.Gray, // atau color yang kamu pakai
            thickness = 1.dp
        )
    }
}


@Preview
@Composable
private fun TextFieldOtpElementPreview() {
    SmartParkingTheme (dynamicColor = false) {
        TextFieldOtpElement(
            value = "",
            onValueChange = {},
            focusManager = LocalFocusManager.current
        )
    }
}
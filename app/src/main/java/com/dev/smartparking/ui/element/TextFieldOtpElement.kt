package com.dev.smartparking.ui.element

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
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
    focusRequester: FocusRequester
) {
    TextField(
        modifier = modifier.width(60.dp)
            .height(60.dp)
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .focusRequester(focusRequester),
        textStyle = TextStyle(
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.primary
        ),
        shape = RoundedCornerShape(8.dp).copy(),
        value = value,
        onValueChange = {newValue ->
            if (newValue.length <= 1 && newValue.all {  it.isDigit() }) {
                onValueChange(newValue)
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        maxLines = 1,
    )
}

@Preview
@Composable
private fun TextFieldOtpElementPreview() {
    SmartParkingTheme (dynamicColor = false) {
        TextFieldOtpElement(
            value = "",
            onValueChange = {},
            focusRequester = FocusRequester(),
        )
    }
}
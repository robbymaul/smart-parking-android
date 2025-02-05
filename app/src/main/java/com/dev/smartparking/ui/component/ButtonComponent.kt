package com.dev.smartparking.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev.smartparking.R
import com.dev.smartparking.ui.theme.SmartParkingTheme

@Composable
fun ButtonComponent(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    @StringRes text: Int,
    icon:@Composable  (()-> Unit)? = null,
    cornerRadius: Dp = 8.dp,
    color: Color,
    textColor: Color,
    textSize: TextUnit = 16.sp
) {
    Button (
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(cornerRadius),
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            contentColor = textColor
        )
    ) {
        if (icon != null) {
            icon()
        } else {
            Text(
                text = stringResource(text),
                style = TextStyle(
                    fontSize = textSize,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
    }

}

@Preview
@Composable
private fun ButtonComponentPreview() {
    SmartParkingTheme {
        ButtonComponent(
            text = R.string.button_book_now,
            color = MaterialTheme.colorScheme.primary,
            textColor = MaterialTheme.colorScheme.background,
            onClick = {},
//            icon = {
//                Icon(
//                    imageVector = Icons.Default.ArrowForward,
//                    contentDescription = "icon arrow button",
//                )
//            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}
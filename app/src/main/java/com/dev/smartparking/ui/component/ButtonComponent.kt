package com.dev.smartparking.ui.component

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev.smartparking.ui.theme.SmartParkingTheme

@Composable
fun ButtonComponent(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String = "",
    icon:@Composable  (()-> Unit)? = null,
    cornerRadius: Dp = 8.dp,
    color: Color,
    textColor: Color
) {
    Button (
        modifier = modifier.padding(8.dp),
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
                text = text,
                style = TextStyle(
                    fontSize = 22.sp,
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
            text = "Register",
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
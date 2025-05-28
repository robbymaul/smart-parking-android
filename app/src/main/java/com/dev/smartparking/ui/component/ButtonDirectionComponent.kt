package com.dev.smartparking.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Directions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dev.smartparking.ui.theme.SmartParkingTheme

@Composable
fun ButtonDirectionComponent(
    modifier: Modifier = Modifier,
    icon: @Composable ()-> Unit,
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = {
            onClick()
        }
    ) {
        icon()
        Text(
            text = text
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ButtonDirectionComponentPreview() {
    SmartParkingTheme {
        ButtonDirectionComponent(
            text = "",
            icon = {
                Icon(
                    imageVector = Icons.Default.Directions,
                    contentDescription = ""
                )
            }
        ) {}
    }
}
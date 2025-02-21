package com.dev.smartparking.ui.component

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Directions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.dev.smartparking.R
import com.dev.smartparking.ui.theme.SmartParkingTheme

@Composable
fun ButtonDirectionComponent(
    modifier: Modifier = Modifier,
    icon: @Composable ()-> Unit,
    @StringRes text: Int
) {
    Button(
        onClick = {}
    ) {
        icon()
        Text(
            text = stringResource(text)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ButtonDirectionComponentPreview() {
    SmartParkingTheme {
        ButtonDirectionComponent(
            text = R.string.txt_login,
            icon = {
                Icon(
                    imageVector = Icons.Default.Directions,
                    contentDescription = ""
                )
            }
        )
    }
}
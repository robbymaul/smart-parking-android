package com.dev.smartparking.ui.element

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Directions
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.smartparking.R
import com.dev.smartparking.ui.component.ButtonDirectionComponent
import com.dev.smartparking.ui.theme.SmartParkingTheme

@Composable
fun MallDirectionButtonElement(modifier: Modifier = Modifier) {
    Column (
        modifier = modifier.fillMaxWidth(),
    ) {
        Row (
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ButtonDirectionComponent(
                text = R.string.txt_login,
                icon = {
                    Icon(
                        imageVector = Icons.Default.Directions,
                        contentDescription = ""
                    )
                }
            )
            ButtonDirectionComponent(
                text = R.string.txt_login,
                icon = {
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = ""
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MallDirectionButtonElementPreview() {
    SmartParkingTheme {
        MallDirectionButtonElement()
    }
}
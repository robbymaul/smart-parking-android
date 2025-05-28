package com.dev.smartparking.ui.element

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.smartparking.ui.profile.menu.ProfileMenuAction
import com.dev.smartparking.ui.theme.SmartParkingTheme

@Composable
fun MenuProfileElement(
    modifier: Modifier = Modifier,
    icon: @Composable ()-> Unit,
    title: String = "",
    actions: List<ProfileMenuAction> = emptyList()
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            icon()
            Text(text = title)
        }
        Row {
            actions.forEach { action ->
                Box(
                    modifier = Modifier
                        .size(36.dp) // <-- Tambahkan ukuran
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(bounded = false)
                        ) {
                            action.onClick()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    action.content()
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MenuProfileElementPreview() {
    SmartParkingTheme {
        MenuProfileElement(
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile Icon",
                    modifier = Modifier.size(24.dp)
                )
            },
            title = "My Details",
        )
    }
}
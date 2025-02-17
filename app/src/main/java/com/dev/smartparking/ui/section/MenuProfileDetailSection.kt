package com.dev.smartparking.ui.section

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev.smartparking.ui.element.MenuProfileElement
import com.dev.smartparking.ui.theme.SmartParkingTheme

@Composable
fun MenuProfileDetailSection(
    modifier: Modifier = Modifier,
    title: String = "",
    content: @Composable () -> Unit
) {
    Column (
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = "Profile Details",
            style = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
            )
        )
        content()
    }
    
}

@Preview(showBackground = true)
@Composable
private fun ProfileDetailSectionPreview() {
    SmartParkingTheme {
        MenuProfileDetailSection() {
            MenuProfileElement(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Profile Icon",
                        modifier = Modifier.size(24.dp)
                    )
                },
                title = "My Details",
                action = {
                    IconButton(onClick = { /* TODO: Handle edit action */ }) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Edit Icon",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            )
        }
    }
}
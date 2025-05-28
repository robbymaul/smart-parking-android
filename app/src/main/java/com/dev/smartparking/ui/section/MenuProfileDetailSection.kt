package com.dev.smartparking.ui.section

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsCar
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
import com.dev.smartparking.ui.profile.menu.ProfileDetailMenu
import com.dev.smartparking.ui.profile.menu.ProfileMenuAction
import com.dev.smartparking.ui.theme.SmartParkingTheme

@Composable
fun MenuProfileDetailSection(
    modifier: Modifier = Modifier,
    title: String = "",
    contents: List<ProfileDetailMenu> = emptyList()
) {
    Column (
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = title,
            style = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
            )
        )
        if (contents.isNotEmpty()) {
            contents.forEach {
                MenuProfileElement(title = it.label, icon = it.icon, actions = it.actions)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileDetailSectionPreview() {
    SmartParkingTheme {
        MenuProfileDetailSection(title = "Profile Details", contents = listOf(
            ProfileDetailMenu(
                icon = {
                    Icon(imageVector = Icons.Filled.DirectionsCar, contentDescription = "user vehicle menu")
                },
                label = "My Vehicle",
                actions = listOf ( ProfileMenuAction(content = { Icon(imageVector = Icons.Filled.Edit, contentDescription = "") }) )
            )
        ))
    }
}
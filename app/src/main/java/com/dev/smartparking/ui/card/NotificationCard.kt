package com.dev.smartparking.ui.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.dev.smartparking.R
import com.dev.smartparking.ui.section.NotificationSection
import com.dev.smartparking.ui.theme.SmartParkingTheme

@Composable
fun NotificationCard(modifier: Modifier = Modifier) {
    Row (
        modifier = modifier.fillMaxWidth()
            .padding(8.dp)
            .heightIn(max = 120.dp)
            .background(
                color = colorResource(R.color.bg_notification_unread).copy(alpha = 0.15f),
                shape = RoundedCornerShape(percent = 8)
            ),
        horizontalArrangement = Arrangement.spacedBy(space = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.image_default_avatar1),
            contentDescription = "",
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(Color.LightGray),
            contentScale = ContentScale.Crop
        )
        NotificationSection(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            name = "Robby Maulana",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                    "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                    "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. " +
                    "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. " +
                    "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. " +
                    "Curabitur pretium tincidunt lacus. Nulla gravida orci a odio. Nullam varius, turpis et commodo pharetra, " +
                    "est eros bibendum elit, nec luctus magna felis sollicitudin mauris. Integer in mauris eu nibh euismod gravida. " +
                    "Duis ac tellus et risus vulputate vehicula. " +
                    "Donec lobortis risus a elit. Etiam tempor. Ut ullamcorper, ligula eu tempor congue, eros est euismod turpis, id tincidunt sapien risus a quam. " +
                    "Maecenas fermentum consequat mi. " +
                    "Donec fermentum. Pellentesque malesuada nulla a mi. " +
                    "Duis sapien sem, aliquet nec, commodo eget, consequat quis, neque. " +
                    "Aliquam faucibus, elit ut dictum aliquet, felis nisl adipiscing sapien, sed malesuada diam lacus eget erat. " +
                    "Cras mollis scelerisque nunc. Nullam arcu. Aliquam consequat. Curabitur augue lorem, dapibus quis, laoreet et, pretium ac, nisi." +
                    " Aenean magna nisl, mollis quis, molestie eu, feugiat in, orci. In hac habitasse platea dictumst."
        )
    }
    Divider(color = Color.Gray, thickness = 1.dp)
}

@Preview(showBackground = true)
@Composable
private fun NotificationCardPreview() {
    SmartParkingTheme {
        NotificationCard()
    }
}
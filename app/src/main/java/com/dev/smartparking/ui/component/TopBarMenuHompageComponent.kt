package com.dev.smartparking.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dev.smartparking.R
import com.dev.smartparking.route.Screen
import com.dev.smartparking.ui.theme.SmartParkingTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarMenuHomepageComponent(modifier: Modifier = Modifier, navController: NavController) {
    TopAppBar(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
            .clip(shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)),
        title = {
            Box( // Memastikan title tetap di tengah secara vertikal
                modifier = Modifier.fillMaxHeight(),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.LocationOn, contentDescription = "Location")
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "Your Location")
                }
            }
        },
        colors = TopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            scrolledContainerColor = MaterialTheme.colorScheme.primary,
            navigationIconContentColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.background,
            actionIconContentColor = MaterialTheme.colorScheme.secondary,
        ),
        actions = {
            Box(
                modifier = Modifier.fillMaxHeight().padding(end = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                IconButton(onClick = { navController.navigate(Screen.DetailProfile.route) }) {
                    Image(
                        painter = painterResource(id = R.drawable.image_default_avatar1),
                        contentDescription = "Profile",
                        modifier = Modifier.size(100.dp)
                    )
                }
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
private fun TopBarMenuHomepageComponentPreview() {
    SmartParkingTheme {
        TopBarMenuHomepageComponent(navController = rememberNavController())
    }
}
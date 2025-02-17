package com.dev.smartparking.ui.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.smartparking.R
import com.dev.smartparking.ui.component.ButtonComponent
import com.dev.smartparking.ui.element.MenuProfileElement
import com.dev.smartparking.ui.section.MenuProfileDetailSection
import com.dev.smartparking.ui.theme.SmartParkingTheme

@Composable
fun MenuProfileCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 8.dp
        ),
        shape = RoundedCornerShape(15.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp) // Tambahkan padding dalam agar konten tidak terlalu menempel dengan tepi Card
                .fillMaxWidth()
        ) {
            // Batasi tinggi LazyColumn agar tidak memakan semua ruang
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f) // Tambahkan weight agar tidak memakan seluruh tinggi Card
            ) {
                items(count = 15) {
                    MenuProfileDetailSection {
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
                                IconButton(onClick = { }) {
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

            Spacer(modifier = Modifier.height(16.dp)) // Jarak antara LazyColumn dan tombol

            OutlinedButton(
                onClick = { },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(percent = 15),
                border = BorderStroke(2.dp, Color.Black),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.Transparent, // Transparan
                    contentColor = Color.Black
                )
            ) {
                Text(text = stringResource(id = R.string.txt_button_login1), color = Color.Black)
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
private fun MenuProfileCardPreview() {
    SmartParkingTheme {
        MenuProfileCard()
    }
}
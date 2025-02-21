package com.dev.smartparking.activity

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.smartparking.R
import com.dev.smartparking.ui.theme.SmartParkingTheme

@Composable
fun ReviewsCard(
    modifier: Modifier = Modifier,
    rating: Int // Tambahkan parameter rating
) {
    Column (
        modifier = modifier
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp)
                .padding(8.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                Image(
                    painter = painterResource(R.drawable.image_default_avatar1),
                    contentDescription = ""
                )
                Column(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Robby Maulana")
                        StarRating(rating) // Menampilkan bintang sesuai rating
                    }
                    Text(
                        text = "Lorem ipsum dolor sitamter Lorem ipsum dolor sitamter Lorem ipsum dolor sitamterLorem ipsum dolor sitamter" +
                                "Lorem ipsum dolor sitamterLorem ipsum dolor sitamterLorem ipsum dolor sitamter" +
                                "Lorem ipsum dolor sitamter Lorem ipsum dolor sitamter" +
                                "Lorem ipsum dolor sitamter" +
                                "Lorem ipsum dolor sitamter",
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
        Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(top = 4.dp))
    }
}

@Composable
private fun StarRating(rating: Int) {
    Row {
        repeat(5) { index ->
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = if (index < rating) Color.Yellow else Color.Gray,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ReviewsCardPreview() {
    SmartParkingTheme {
        ReviewsCard(
            rating = 4
        )
    }
}
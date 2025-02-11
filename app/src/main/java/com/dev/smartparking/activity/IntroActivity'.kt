package com.dev.smartparking.activity

import android.graphics.Color
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.smartparking.R
import com.dev.smartparking.ui.component.ButtonComponent
import com.dev.smartparking.ui.screen.IntroScreen
import com.dev.smartparking.ui.theme.SmartParkingTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.Box as Box1

@OptIn(ExperimentalPagerApi::class)
@Composable
fun IntroActivity(modifier: Modifier = Modifier) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    val listScreen: List<IntroScreenData> = IntroScreenProvider.getIntroScreens()

    Column {
        HorizontalPager(
            count = listScreen.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { page ->
            Box1(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                IntroScreen(
                    title = listScreen[page].title,
                    description = listScreen[page].description,
                    image = listScreen[page].image
                )
            }
        }

        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)
        ){

            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .padding(16.dp)
            )

            when {
                pagerState.currentPage < listScreen.size - 1 -> {
                    ButtonComponent(
                        modifier = Modifier.padding(8.dp),
                        color = MaterialTheme.colorScheme.primary,
                        textColor = MaterialTheme.colorScheme.background,
                        onClick = {
                            if (pagerState.currentPage < listScreen.size -1) {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                }
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowForward,
                                contentDescription = "icon arrow button",
                            )
                        },
                    )
                }
                else -> {
                    ButtonComponent(
                        modifier = Modifier.padding(8.dp),
                        color = MaterialTheme.colorScheme.primary,
                        textColor = MaterialTheme.colorScheme.background,
                        onClick = {
                            if (pagerState.currentPage < listScreen.size -1) {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                }
                            }
                        },
                        text = R.string.txt_button_get_started
                    )
                }
            }
        }
    }
}

data class IntroScreenData(
    val title: Int,
    val description: Int,
    val image: Int
)

object IntroScreenProvider {
    fun getIntroScreens(): List<IntroScreenData> {
        return listOf(
            IntroScreenData(
                title = R.string.title_screen_easy_parking,
                description = R.string.desc_screen_easy_parking,
                image = R.drawable.smart_parking_logo1
            ),
            IntroScreenData(
                title = R.string.title_screen_book_anytime_anywhere,
                description = R.string.desc_screen_book_anytime_anywhere,
                image = R.drawable.image_mall1
            ),
            IntroScreenData(
                title = R.string.title_screen_safe_and_secure,
                description = R.string.desc_screen_safe_and_secure,
                image = R.drawable.image_mall1
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun IntroActivityPreview() {
    SmartParkingTheme {
        IntroActivity()
    }
}
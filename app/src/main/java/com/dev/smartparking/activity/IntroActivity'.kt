package com.dev.smartparking.activity

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dev.smartparking.R
import com.dev.smartparking.ui.component.ButtonComponent
import com.dev.smartparking.ui.screen.IntroScreen
import com.dev.smartparking.ui.theme.SmartParkingTheme
import com.dev.smartparking.viewmodel.IntroViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun IntroActivity(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    viewModel: IntroViewModel = viewModel()
) {
    val listScreen by viewModel.listenScreen.collectAsState()
    val pagerState = rememberPagerState(initialPage = viewModel.currentPage)
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = modifier) {
        HorizontalPager(
            count = listScreen.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { page ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                IntroScreen(
                    title = stringResource(listScreen[page].title),
                    description = stringResource(listScreen[page].description) ,
                    image = painterResource(listScreen[page].image)
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier.padding(16.dp),
                activeColor = MaterialTheme.colorScheme.primary
            )

            when {
                pagerState.currentPage < listScreen.size - 1 -> {
                    ButtonComponent(
                        modifier = Modifier.padding(8.dp),
                        textColor = MaterialTheme.colorScheme.background,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowForward,
                                contentDescription = "Next page"
                            )
                        }
                    )
                }
                else -> {
                    ButtonComponent(
                        modifier = Modifier.padding(8.dp),
                        textColor = MaterialTheme.colorScheme.background,
                        onClick = { viewModel.navigateToLogin(navController) },
                        text = R.string.txt_button_get_started
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun IntroActivityPreview() {
    SmartParkingTheme {
        IntroActivity()
    }
}
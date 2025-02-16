package com.dev.smartparking.ui.section

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import coil.compose.AsyncImage
import com.dev.smartparking.R
import com.dev.smartparking.ui.theme.SmartParkingTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BannerSection(modifier: Modifier = Modifier) {
    val pagerState = rememberPagerState()

    val images = listOf(
        R.drawable.image_banner1,
        R.drawable.image_banner1,
        R.drawable.image_banner1,
        R.drawable.image_banner1,
        R.drawable.image_banner1,
    )

    LaunchedEffect(Unit) {
        while (true) {
            yield()
            delay(3000)
            val nextPage = (pagerState.currentPage + 1) % images.size
            pagerState.animateScrollToPage(nextPage)
        }
    }

    AutoSlidingCarousel(
        pagerState = pagerState,
        banner = images
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AutoSlidingCarousel(
    modifier: Modifier = Modifier,
    pagerState: PagerState = remember { PagerState() },
    banner: List<Int>
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        HorizontalPager(
            count = banner.size,
            state = pagerState
        ) { page ->
            Image(
                painter = painterResource(banner[page]),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp, bottom = 6.dp)
                    .height(150.dp)
                    .clip(RoundedCornerShape(percent = 10))
            )
        }

        DotIndicator(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .align(Alignment.CenterHorizontally),
            totalDots = banner.size,
            selectedIndex = pagerState.currentPage,
            dotSize = 6.dp
        )

    }
}

@Composable
fun DotIndicator(
    modifier: Modifier = Modifier,
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color = colorResource(R.color.black),
    unselectedColor: Color = colorResource(R.color.grey).copy(alpha = 0.5f),
    dotSize: Dp,
) {
    LazyRow (
        modifier = modifier
            .wrapContentSize()
    ) { items(totalDots) { index ->
        IndicatorDot(
            color = if (index == selectedIndex) selectedColor else unselectedColor,
            size = dotSize,
        )
        if (index != totalDots -1 ) {
            Spacer(
                modifier = Modifier.padding(horizontal = 2.dp)
            )
        }
    }

    }
}

@Composable
fun IndicatorDot(
    modifier: Modifier = Modifier,
    size: Dp,
    color: Color,
) {
    Box (
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(color)

    ) {

    }
    
}

@Preview(showBackground = true)
@Composable
private fun BannerSectionPreview() {
    SmartParkingTheme {
        BannerSection()
    }
}
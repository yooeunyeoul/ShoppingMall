@file:OptIn(ExperimentalPagerApi::class)

package com.sample.shoppingmall.presentation.shoppingHome.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.sample.domain.model.Banner
import com.sample.shoppingmall.presentation.components.dpToSp

@Composable
fun horizontalBanner(pagerState: PagerState, banners: List<Banner>) {
    Box(Modifier.fillMaxWidth()) {
        HorizontalPager(
            count = Int.MAX_VALUE,
            state = pagerState
        ) { page ->
            if (banners.isNotEmpty()) {
                val currentPage = page % banners.count()
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(banners[currentPage].imageUrl)
                        .crossfade(true).build(),
                    contentScale = ContentScale.FillWidth,
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        if (banners.isNotEmpty()) {
            Box(
                Modifier
                    .padding(end = 16.dp, bottom = 8.dp)
                    .align(Alignment.BottomEnd)
                    .background(
                        color = Color.Black.copy(alpha = 0.30f),
                        shape = RoundedCornerShape(16.dp)
                    )
            ) {
                val bannerCount = banners.count()
                Text(
                    text = "${(pagerState.currentPage % bannerCount) + 1}/${bannerCount}",
                    color = Color.White,
                    modifier = Modifier.padding(
                        start = 9.dp,
                        end = 9.dp,
                        top = 4.dp,
                        bottom = 4.dp
                    ), fontSize = dpToSp(dp = 12.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewHorizontalBanner() {
    horizontalBanner(
        pagerState = rememberPagerState(),
        banners = listOf(Banner(bannerNo = 0, imageUrl = ""))
    )
}
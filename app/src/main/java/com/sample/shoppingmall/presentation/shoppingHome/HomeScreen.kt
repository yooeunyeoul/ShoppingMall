@file:OptIn(ExperimentalPagerApi::class, ExperimentalPagerApi::class)

package com.sample.shoppingmall.presentation.shoppingHome

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.sample.domain.util.Resource
import com.sample.shoppingmall.presentation.shoppingHome.components.CategoryListLazyColumn
import com.sample.shoppingmall.presentation.shoppingHome.components.FeedListLazyColumn
import com.sample.shoppingmall.presentation.util.HomeTabType
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {

    val bannerState by viewModel.bannerList.collectAsStateWithLifecycle()
    val context = LocalContext.current

    BoxWithConstraints {
        val screenHeight = maxHeight
        val scrollState = rememberScrollState()

        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(state = scrollState)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray), contentAlignment = Alignment.Center
            ) {
                when (bannerState) {
                    is Resource.Error -> {
                        Toast.makeText(
                            context,
                            "Error: " + bannerState.message,
                            Toast.LENGTH_LONG
                        ).show()

                        HorizontalPager(count = bannerState.data?.count() ?: 0) { page ->
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(bannerState.data?.get(page)?.imageUrl)
                                    .crossfade(true).build(),
                                contentScale = ContentScale.FillWidth,
                                contentDescription = null,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }

                    is Resource.Loading -> {
                        CircularProgressIndicator()
                    }

                    is Resource.Success -> {
                        HorizontalPager(count = bannerState.data?.count() ?: 0) { page ->
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(bannerState.data?.get(page)?.imageUrl)
                                    .crossfade(true).build(),
                                contentScale = ContentScale.FillWidth,
                                contentDescription = null,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }

            }

            Column(modifier = Modifier.height(screenHeight)) {
                val pagerState = rememberPagerState(initialPage = 0)
                val coroutineScope = rememberCoroutineScope()

                TabRow(
                    selectedTabIndex = pagerState.currentPage,
                    indicator = {},
                    modifier = Modifier.wrapContentSize(),
                    tabs = {
                        HomeTabType.values().forEachIndexed { index, categoryType ->
                            Tab(selected = pagerState.currentPage == index, onClick = {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(index)
                                }
                            }) {
                                Text(
                                    text = categoryType.name,
                                    color = if (pagerState.currentPage == index) Color.Black else Color.LightGray,
                                    modifier = Modifier.padding(vertical = 20.dp),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                )
                            }
                        }
                    })
                HorizontalPager(
                    state = pagerState,
                    count = HomeTabType.values().size,
                    modifier = Modifier
                        .fillMaxHeight()
                        .nestedScroll(remember {
                            object : NestedScrollConnection {
                                override fun onPreScroll(
                                    available: Offset,
                                    source: NestedScrollSource
                                ): Offset {
                                    return if (available.y > 0) Offset.Zero else Offset(
                                        x = 0f,
                                        y = -scrollState.dispatchRawDelta(-available.y)
                                    )
                                }
                            }
                        })
                ) { page: Int ->
                    when (page) {
                        HomeTabType.MEN.index -> {
                            val menList = viewModel.menList.collectAsLazyPagingItems()
                            CategoryListLazyColumn(menList)
                        }

                        HomeTabType.WOMEN.index -> {
                            val womenList = viewModel.womenList.collectAsLazyPagingItems()
                            CategoryListLazyColumn(womenList)
                        }

                        HomeTabType.HOME.index -> {
                            val homeList = viewModel.homeList.collectAsLazyPagingItems()
                            CategoryListLazyColumn(homeList)
                        }

                        HomeTabType.TECH.index -> {
                            val techList = viewModel.techList.collectAsLazyPagingItems()
                            CategoryListLazyColumn(techList)
                        }

                        HomeTabType.BESTFEED.index -> {
                            val feedList = viewModel.feedList.collectAsLazyPagingItems()
                            FeedListLazyColumn(feedList)
                        }
                    }
                }
            }
        }
    }
}



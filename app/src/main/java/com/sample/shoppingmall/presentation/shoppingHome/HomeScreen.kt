
package com.sample.shoppingmall.presentation.shoppingHome

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.collectIsDraggedAsState
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.sample.common_utils.utils.Resource
import com.sample.shoppingmall.presentation.components.dpToSp
import com.sample.shoppingmall.presentation.shoppingHome.components.CategoryListLazyColumn
import com.sample.shoppingmall.presentation.shoppingHome.components.FeedListLazyColumn
import com.sample.shoppingmall.presentation.shoppingHome.components.horizontalBanner
import com.sample.shoppingmall.presentation.util.HomeTabType
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val bannerState by viewModel.bannerList.collectAsStateWithLifecycle()
    val context = LocalContext.current

    var count by remember {
        mutableStateOf(0)
    }
    val bannerPagerState = rememberPagerState(initialPage = 200)

    val scrollState = rememberScrollState()

    val isDragged by bannerPagerState.interactionSource.collectIsDraggedAsState()

    val productPagerState by viewModel.pagerState.collectAsStateWithLifecycle()

    val coroutineScope = rememberCoroutineScope()


    if (!isDragged) {
        LaunchedEffect(bannerState) {
            count = bannerState.data?.count() ?: 0
            while (true) {
                delay(2.5.seconds)
                with(bannerPagerState) {
                    try {
                        animateScrollToPage(
                            page = currentPage + 1
                        )
                    } catch (e: Exception) {
                        Log.e("error message", e.message ?: "")
                    }
                }
            }
        }
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_STOP) {
                viewModel.onEvent(ShoppingHomeEvents.SaveTabIndex)
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }


    BoxWithConstraints {
        val screenHeight = maxHeight

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

                        horizontalBanner(
                            pagerState = bannerPagerState,
                            banners = bannerState.data ?: listOf()
                        )
                    }

                    is Resource.Loading -> {
                        CircularProgressIndicator()
                    }

                    is Resource.Success -> {
                        horizontalBanner(
                            pagerState = bannerPagerState,
                            banners = bannerState.data ?: listOf()
                        )
                    }
                }
            }

            Column(modifier = Modifier.height(screenHeight)) {

                TabRow(
                    selectedTabIndex = productPagerState.currentPage,
                    indicator = {},
                    modifier = Modifier.wrapContentSize(),
                    tabs = {
                        HomeTabType.values().forEachIndexed { index, categoryType ->
                            Tab(selected = productPagerState.currentPage == index, onClick = {
                                coroutineScope.launch {
                                    productPagerState.animateScrollToPage(index)
                                }
                            }) {
                                Text(
                                    text = categoryType.name,
                                    color = if (productPagerState.currentPage == index) Color.Black else Color.LightGray,
                                    modifier = Modifier.padding(vertical = 20.dp),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = dpToSp(dp = 12.dp)
                                )
                            }
                        }
                    })
                HorizontalPager(
                    state = productPagerState,
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
                            CategoryListLazyColumn(menList, onHeartClick = { category ->
                                if (category.isFavorite) {
                                    viewModel.onEvent(ShoppingHomeEvents.DeleteFavorite(category))
                                } else {
                                    viewModel.onEvent(ShoppingHomeEvents.AddFavorite(category))
                                }

                            })
                        }

                        HomeTabType.WOMEN.index -> {
                            val womenList = viewModel.womenList.collectAsLazyPagingItems()
                            CategoryListLazyColumn(womenList, onHeartClick = { category ->
                                if (category.isFavorite) {
                                    viewModel.onEvent(ShoppingHomeEvents.DeleteFavorite(category))
                                } else {
                                    viewModel.onEvent(ShoppingHomeEvents.AddFavorite(category))
                                }
                            })
                        }

                        HomeTabType.HOME.index -> {
                            val homeList = viewModel.homeList.collectAsLazyPagingItems()
                            CategoryListLazyColumn(homeList, onHeartClick = { category ->
                                if (category.isFavorite) {
                                    viewModel.onEvent(ShoppingHomeEvents.DeleteFavorite(category))
                                } else {
                                    viewModel.onEvent(ShoppingHomeEvents.AddFavorite(category))
                                }
                            })
                        }

                        HomeTabType.TECH.index -> {
                            val techList = viewModel.techList.collectAsLazyPagingItems()
                            CategoryListLazyColumn(techList, onHeartClick = { category ->
                                if (category.isFavorite) {
                                    viewModel.onEvent(ShoppingHomeEvents.DeleteFavorite(category))
                                } else {
                                    viewModel.onEvent(ShoppingHomeEvents.AddFavorite(category))
                                }
                            })
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



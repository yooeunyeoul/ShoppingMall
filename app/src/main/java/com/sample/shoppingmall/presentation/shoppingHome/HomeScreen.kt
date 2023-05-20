@file:OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)

package com.sample.shoppingmall.presentation.shoppingHome

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.sample.domain.util.Resource
import com.sample.shoppingmall.R

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {

    val bannerState by viewModel.bannerList.collectAsStateWithLifecycle()
    var selectedIndex by remember {
        mutableStateOf(0)
    }


    LazyColumn(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        item {
            when (bannerState) {
                is Resource.Error -> {

                }

                is Resource.Loading -> {
                    CircularProgressIndicator()
                }

                is Resource.Success -> {
                    HorizontalPager(count = bannerState.data?.count() ?: 0) { page ->
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
//                            .data(bannerState.data?.get(page)?.imageUrl)
                                .data(R.drawable.sample)
                                .crossfade(true).build(),
                            contentScale = ContentScale.FillWidth,
                            contentDescription = null,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
        stickyHeader {
            TabRow(selectedTabIndex = selectedIndex, modifier = Modifier.height(48.dp)) {
                Tab(selected = selectedIndex == 0, onClick = { selectedIndex = 0 }) {
                    Text(text = "WOMEN")
                }
                Tab(selected = selectedIndex == 1, onClick = { selectedIndex = 1 }) {
                    Text(text = "MEN")
                }
                Tab(selected = selectedIndex == 2, onClick = { selectedIndex = 2 }) {
                    Text(text = "HOME")
                }
                Tab(selected = selectedIndex == 3, onClick = { selectedIndex = 3 }) {
                    Text(text = "TECH")
                }
                Tab(selected = selectedIndex == 4, onClick = { selectedIndex = 4 }) {
                    Text(text = "BEST FEED")
                }
            }
        }

    }

}
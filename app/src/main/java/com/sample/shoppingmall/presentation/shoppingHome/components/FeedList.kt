package com.sample.shoppingmall.presentation.shoppingHome.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems
import com.sample.domain.model.Feed

@Composable
fun FeedListLazyColumn(feedItems: LazyPagingItems<Feed>) {
    LazyColumn(
    ) {
        items(feedItems.itemCount, key = {index -> feedItems[index]?.feedNo?:0  }) { index ->
            feedItems[index]?.let { feedItem -> FeedItem(feed = feedItem) }
        }
    }
}
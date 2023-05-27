package com.sample.shoppingmall.presentation.shoppingHome.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sample.domain.model.Feed
import com.sample.shoppingmall.presentation.components.CrossFadeImage
import com.sample.shoppingmall.presentation.components.dpToSp

@Composable
fun FeedItem(feed: Feed) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(start = 50.dp)
    ) {

        CrossFadeImage(
            contentScale = ContentScale.FillWidth,
            url = feed.imageUrl
        )

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = feed.feedTitle,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = dpToSp(dp =28.dp )
        )

        Text(
            text = feed.feedContents,
            color = Color.Gray,
            fontSize = dpToSp(dp =16.dp )
        )


        Spacer(modifier = Modifier.height(8.dp))


    }
}

@Preview
@Composable
fun PreviewFeedItem() {
    FeedItem(feed = Feed(feedContents = "컨텐츠", feedTitle = "타이틀", imageUrl = "https://img.29cm.co.kr//next-product//2020//11//18//de8724db0618460d9dc7a9a99125f8e7_20201118144729.jpg"))
}

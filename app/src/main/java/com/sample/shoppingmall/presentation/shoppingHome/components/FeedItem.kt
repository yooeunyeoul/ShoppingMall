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
import androidx.compose.ui.unit.sp
import com.sample.domain.model.Feed
import com.sample.shoppingmall.presentation.components.CrossFadeImage

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
            fontSize = 28.sp
        )

        Text(
            text = feed.feedContents,
            color = Color.Gray,
            fontSize = 16.sp
        )


        Spacer(modifier = Modifier.height(8.dp))


    }
}

@Preview
@Composable
fun PreviewFeedItem() {
    FeedItem(feed = Feed())
}
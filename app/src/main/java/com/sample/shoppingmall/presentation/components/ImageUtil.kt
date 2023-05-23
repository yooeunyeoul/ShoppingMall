package com.sample.shoppingmall.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun CrossFadeImage(
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.FillWidth,
    url: String
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true).build(),
        contentScale = contentScale,
        contentDescription = null,
        modifier = modifier
            .fillMaxWidth()
    )

}
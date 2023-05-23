package com.sample.shoppingmall.presentation.shoppingHome.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sample.domain.model.Category
import com.sample.shoppingmall.R

@Composable
fun HeartIcon(modifier: Modifier = Modifier, category: Category?) {
    Icon(
        painter = painterResource(
            id = if (category?.isFavorite == true) R.drawable.icon_heartfilled
            else R.drawable.icon_heart
        ),
        contentDescription = null,
        modifier = modifier
            .size(20.dp),
        tint = if (category?.isFavorite == true) Color.Red else Color.Gray
    )
}

@Preview
@Composable
fun PreviewHeartIcon() {
    HeartIcon(category = Category(isFavorite = true))
}
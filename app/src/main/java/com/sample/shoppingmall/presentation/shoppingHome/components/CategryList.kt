package com.sample.shoppingmall.presentation.shoppingHome.components

import android.util.Log
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems
import com.sample.domain.model.Category

@Composable
fun CategoryListLazyColumn(
    categoryItems: LazyPagingItems<Category>,
    onHeartClick: (Category) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
    ) {
        items(
            categoryItems.itemCount,
            contentType = { categoryItems[it]?.categoryType }, key = { index ->
                if (categoryItems[categoryItems.itemCount - 1] == null || categoryItems.itemCount <= 1) index else categoryItems[index]?.itemNo
                    ?: index

            }
        ) { index ->
            categoryItems[index]?.let { category ->
                CategoryItem(category, onItemClick = { category ->
                    onHeartClick(category)
                })
            }
        }
    }

}

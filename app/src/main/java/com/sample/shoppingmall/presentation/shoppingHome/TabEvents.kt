package com.sample.shoppingmall.presentation.shoppingHome

import com.sample.domain.util.CategoryType
import com.sample.domain.util.FeedType


sealed class TabEvents {
    data class SelectCategoryTab(val categoryType: CategoryType) : TabEvents()
    data class SelectFeedTab(val feedType: FeedType) : TabEvents()
}
package com.sample.shoppingmall.presentation.shoppingHome

import com.sample.domain.model.Category

sealed class ShoppingHomeEvents {
    data class AddFavorite(val category: Category) : ShoppingHomeEvents()
    data class DeleteFavorite(val category: Category) : ShoppingHomeEvents()
    object SaveTabIndex : ShoppingHomeEvents()
}
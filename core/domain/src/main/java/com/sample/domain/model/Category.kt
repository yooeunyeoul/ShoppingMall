package com.sample.domain.model

import com.sample.common_utils.utils.CategoryType

data class Category(
    val brandName: String="",
    val heartCount: Int=0,
    val imageUrl: String="",
    val itemName: String="",
    val itemNo: Int,
    val reviewAveragePoint: Double= 0.0,
    val reviewCount: Int=0,
    val saleInfo: SaleInfo = SaleInfo(),
    val isFavorite: Boolean = false,
    val categoryType: CategoryType
)
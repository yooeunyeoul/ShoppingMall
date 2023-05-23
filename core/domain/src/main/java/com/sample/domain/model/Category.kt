package com.sample.domain.model

data class Category(
    val brandName: String="",
    val heartCount: Int=0,
    val imageUrl: String="",
    val itemName: String="",
    val itemNo: Int=0,
    val reviewAveragePoint: Double= 0.0,
    val reviewCount: Int=0,
    val saleInfo: SaleInfo = SaleInfo(),
    var isFavorite: Boolean = false
)
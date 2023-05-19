package com.sample.domain.model

data class Category(
    val brandName: String,
    val heartCount: Int,
    val imageUrl: String,
    val itemName: String,
    val itemNo: Int,
    val reviewAveragePoint: Double,
    val reviewCount: Int,
    val saleInfo: SaleInfo
)
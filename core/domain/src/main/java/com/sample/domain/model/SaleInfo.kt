package com.sample.domain.model

data class SaleInfo(
    val consumerPrice: Int,
    val couponSaleRate: Int,
    val isCoupon: Boolean,
    val saleRate: Int,
    val sellPrice: Int,
    val totalSaleRate: Int,
    val totalSellPrice: Int
)
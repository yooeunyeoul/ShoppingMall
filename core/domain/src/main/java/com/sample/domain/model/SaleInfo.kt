package com.sample.domain.model

data class SaleInfo(
    val consumerPrice: Int=0,
    val couponSaleRate: Int=0,
    val isCoupon: Boolean= false,
    val saleRate: Int=0,
    val sellPrice: Int=0,
    val totalSaleRate: Int=0,
    val totalSellPrice: Int=0
)
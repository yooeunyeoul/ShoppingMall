package com.sample.network.model

data class SaleInfo(
    val consumer_price: Int,
    val coupon_sale_rate: Int,
    val is_coupon: Boolean,
    val sale_rate: Int,
    val sell_price: Int,
    val total_sale_rate: Int,
    val total_sell_price: Int
)
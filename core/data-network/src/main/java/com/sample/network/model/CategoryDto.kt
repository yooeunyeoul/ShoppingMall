package com.sample.network.model

data class CategoryDto (
    val brand_name: String,
    val heart_count: Int,
    val image_url: String,
    val item_name: String,
    val item_no: Int,
    val review_average_point: Double,
    val review_count: Int,
    val sale_info: SaleInfo
)

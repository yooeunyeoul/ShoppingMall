package com.sample.network.model

import com.sample.network.base.BaseListResponse

data class CategoryResponse(
    val baseListResponse: BaseListResponse<CategoryDto>,
    val total_count: Int
)
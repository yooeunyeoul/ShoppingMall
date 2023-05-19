package com.sample.network.model

import com.sample.network.base.BaseListResponse

data class FeedResponse(
    val baseListResponse: BaseListResponse<FeedDto>,
    val total_count: Int
)
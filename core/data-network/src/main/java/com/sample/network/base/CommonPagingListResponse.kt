package com.sample.network.base

data class CommonPagingListResponse<T>(
    val error_code: Int?=null,
    val list: List<T>,
    val result: String,
    val total_count: Int
)
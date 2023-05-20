package com.sample.network.base

data class CommonListResponse<T>(
    val error_code: Int?,
    val list: List<T>,
    val result: String
)
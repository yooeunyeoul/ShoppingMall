package com.sample.network.base

data class BaseListResponse<T>(
    val error_code: Any?,
    val list: List<T>,
    val result: String
)
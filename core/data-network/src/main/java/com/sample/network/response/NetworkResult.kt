package com.sample.network.response

import com.sample.domain.response.NetworkResult
import com.sample.network.base.CommonListResponse
import com.sample.network.base.CommonPagingListResponse

fun <T> CommonListResponse<T>.toNetworkResult(): NetworkResult<List<T>> {
    return when (result) {
        "SUCCESS" -> NetworkResult.Success(list)
        else -> NetworkResult.Fail(data = null, message = "Network Error, ErrorCode : $error_code")
    }
}

fun <T> CommonPagingListResponse<T>.toNetworkResult(): NetworkResult<List<T>> {
    return when (result) {
        "SUCCESS" -> NetworkResult.Success(list)
        else -> NetworkResult.Fail(data = null, message = "Network Error, ErrorCode : $error_code")
    }
}

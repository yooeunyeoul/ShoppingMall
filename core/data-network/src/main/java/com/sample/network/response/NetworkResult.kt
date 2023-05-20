package com.sample.network.response

import com.sample.network.base.BaseListResponse

fun <T> BaseListResponse<T>.toNetworkResult(): NetworkResult<List<T>> {
    return when (result) {
        "SUCCESS" -> NetworkResult.Success(list)
        else -> NetworkResult.Fail(data = null, message = "Network Error, ErrorCode : $error_code")
    }
}

sealed class NetworkResult<out DATA> {
    data class Success<DATA>(var data: DATA) : NetworkResult<DATA>()
    data class Fail<DATA>(var data: DATA?, val message: String?) : NetworkResult<DATA>()
}
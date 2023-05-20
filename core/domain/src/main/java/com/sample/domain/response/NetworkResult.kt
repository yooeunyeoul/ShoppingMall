package com.sample.domain.response


sealed class NetworkResult<out DATA> {
    data class Success<DATA>(var data: DATA) : NetworkResult<DATA>()
    data class Fail<DATA>(var data: DATA?, val message: String?) : NetworkResult<DATA>()
}
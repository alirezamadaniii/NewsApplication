package com.example.global.network.resource

import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

sealed class Resource<T>(
    var data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data = data)
    class Loading<T>(data: T? = null) : Resource<T>(data = data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data = data, message = message)
}

fun <T> handleException(e: Exception): Resource.Error<T> = when (e) {
    is HttpException -> Resource.Error(message = getErrorMessage(e.code()), null)
    is SocketTimeoutException -> Resource.Error(message = "timeout", null)
    is UnknownHostException -> Resource.Error(message = "Unable to resolve host name")
    else -> Resource.Error(message = getErrorMessage(Int.MAX_VALUE), null)
}

private fun getErrorMessage(code: Int): String = when (code) {
    401 -> "Unauthorised"
    404 -> "Not found"
    else -> "Something went wrong $code"
}


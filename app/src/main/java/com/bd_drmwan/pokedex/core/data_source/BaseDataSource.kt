package com.bd_drmwan.pokedex.core.data_source

import com.bd_drmwan.pokedex.core.model.ErrorTypeEnum
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException

open class BaseDataSource {
    suspend fun <T> validateResponse(
        response: Response<T>,
        onSuccess: suspend (result: T) -> Unit,
        onError: suspend (message: String) -> Unit
    ) {
        if (response.isSuccessful) {
            response.body()?.let {
                onSuccess(it)
            } ?: run { onError("Data is null") }
        } else {
            onError("Internal server error")
        }
    }

    suspend fun validateError(
        error: Throwable,
        messageError: suspend (String, ErrorTypeEnum) -> Unit
    ) {
        when (error) {
            is SocketTimeoutException -> messageError("Socket timeout", ErrorTypeEnum.TIMEOUT)
            is IOException -> messageError("Connection timeout", ErrorTypeEnum.TIMEOUT)
            else -> messageError(error.localizedMessage ?:"Internal server error", ErrorTypeEnum.BASIC)
        }
    }
}

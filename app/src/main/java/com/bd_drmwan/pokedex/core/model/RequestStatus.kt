package com.bd_drmwan.pokedex.core.model

enum class ErrorTypeEnum {
    BASIC,
    NO_CONNECTION,
    TIMEOUT
}

sealed class RequestStatus<T> {
    data class Success<T>(
        val data: T?
    ) : RequestStatus<T>()

    data class Error<T>(
        val message: String,
        val errorType: ErrorTypeEnum = ErrorTypeEnum.BASIC
    ) : RequestStatus<T>()

    class Loading<T> : RequestStatus<T>()
}
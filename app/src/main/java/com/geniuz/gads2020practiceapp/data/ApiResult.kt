package com.geniuz.gads2020practiceapp.data

sealed class ApiResult<T> {
    companion object{
        fun <T>error(errorMessage: String,  throwable: Throwable?) = Error<T>(errorMessage, throwable)
        fun <T>success(data: T) = Success<T>(data)
    }
}

data class Error<T>(
    val errorMessage: String,
    val throwable: Throwable?
):ApiResult<T>()

data class Success<T>(
    val data: T
) : ApiResult<T>()
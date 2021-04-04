package com.thanht.domain.base

sealed class BaseResult<out T> {

    /**
     * Loading....
     */
    object Loading : BaseResult<Nothing>()

    /**
     * Successful API connection then returns data
     * Should be returned to MainThread
     */
    data class Success<T>(
        val status: Int = 200,
        val message: String = "",
        val data: T
    ) : BaseResult<T>()

    /**
     * Successful API connection, but the flag of data is a error flag
     * Error Flag will be handled elsewhere
     */
    data class Failed<T>(
        val status: Int = 0,
        val message: String = "",
        val exception: String = "",
        val data: T?
    ) : BaseResult<T>()

    /**
     * API connection error: HttpConnection, MoshiParser,...
     * Should be returned to MainThread
     */
    data class Error(val error: Throwable) : BaseResult<Nothing>()
}
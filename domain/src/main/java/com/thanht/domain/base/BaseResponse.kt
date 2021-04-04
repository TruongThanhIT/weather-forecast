package com.thanht.domain.base

open class BaseResponse<T>(
    open val status: Int = 0,
    open val message: String = "",
    open val exception: String = "",
    val data: T
)

package com.thanht.data.util

import com.thanht.data.executor.PostExecutionThread
import com.thanht.domain.base.BaseResponse
import com.thanht.domain.base.BaseResult
import kotlinx.coroutines.flow.*

fun <T> flowRes(
    postExecutionThread: PostExecutionThread,
    block: suspend () -> BaseResponse<T>
) = flow { emit(block.invoke()) }
    .map { response ->
        when (response.status) {
            200 -> BaseResult.Success(response.status, response.message, response.data)
            else -> BaseResult.Failed(
                response.status,
                response.message,
                response.exception,
                response.data
            )
        }
    }
    .catch { cause ->
        emit(BaseResult.Error(cause))
    }
    .flowOn(postExecutionThread.io)
    .onStart { emit(BaseResult.Loading) }

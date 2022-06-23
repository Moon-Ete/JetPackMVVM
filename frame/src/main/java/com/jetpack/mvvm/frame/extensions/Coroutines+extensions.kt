package com.jetpack.mvvm.frame.extensions

import com.jetpack.mvvm.frame.network.ApiError
import com.jetpack.mvvm.frame.network.Result
import com.jetpack.mvvm.frame.network.RequestApiBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * @Author: WangXing
 * @CreateDate: 2022-06-21
 * @Email: wangxing1@gwm.cn
 */
fun <T> CoroutineScope.requestApi(
    builder: RequestApiBuilder<T>.() -> Unit
) {
    this.launch(Dispatchers.Main) {
        val delegate = RequestApiBuilder<T>().apply(builder)
        flow {
            delegate.api.invoke().apply {
                emit(
                    if (success) {
                        Result.Success(data)
                    } else {
                        Result.Error(ApiError(errorCode, errorMessage))
                    }
                )
            }
        }
            .onStart {
                delegate.onStart?.invoke()
            }
            .onCompletion {
                delegate.onComplete?.invoke()
            }
            .flowOn(Dispatchers.IO)
            .catch {
                emit(Result.Error(it.convertApiError()))
            }
            .collect {
                if (it is Result.Success) {
                    delegate.onSuccess?.invoke(it.data)
                } else if (it is Result.Error) {
                    delegate.onFailed?.invoke(it.apiError.code, it.apiError.message)
                }
            }
    }
}
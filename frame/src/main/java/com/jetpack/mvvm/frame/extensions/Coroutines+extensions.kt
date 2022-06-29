package com.jetpack.mvvm.frame.extensions

import com.jetpack.mvvm.frame.network.RequestApiBuilder
import com.jetpack.mvvm.frame.network.Result
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
                emit(convertResult())
            }
        }
            .flowOn(Dispatchers.IO)
            .onStart {
                delegate.onStart?.invoke()
            }
            .onCompletion {
                delegate.onComplete?.invoke()
            }
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
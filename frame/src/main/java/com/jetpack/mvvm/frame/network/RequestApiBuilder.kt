package com.jetpack.mvvm.frame.network

/**
 * @Author: WangXing
 * @CreateDate: 2022-06-22
 * @Email: wangxing1@gwm.cn
 */
class RequestApiBuilder<T> {

    internal lateinit var api: (suspend () -> ApiResponse<T>)
        private set

    internal var onSuccess: ((T?) -> Unit)? = null
        private set

    internal var onComplete: (() -> Unit)? = null
        private set

    internal var onStart: (() -> Unit)? = null
        private set

    internal var onFailed: ((code: String, message: String?) -> Unit)? = null
        private set

    fun api(block: suspend () -> ApiResponse<T>) {
        this.api = block
    }

    fun onSuccess(block: (T?) -> Unit) {
        this.onSuccess = block
    }

    fun onComplete(block: () -> Unit) {
        this.onComplete = block
    }

    fun onFailed(block: (code: String, message: String?) -> Unit) {
        this.onFailed = block
    }

    fun onStart(block: () -> Unit) {
        this.onStart = block
    }

}
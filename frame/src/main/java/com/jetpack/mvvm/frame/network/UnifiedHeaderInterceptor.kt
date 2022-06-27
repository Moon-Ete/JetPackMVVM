package com.jetpack.mvvm.frame.network

import com.jetpack.mvvm.frame.extensions.string
import com.tencent.mmkv.MMKV.defaultMMKV
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @Author: WangXing
 * @CreateDate: 2022-06-27
 * @Email: wangxing1@gwm.cn
 */
private const val TOKEN_KEY = "jwt_access_token"

class UnifiedHeaderInterceptor : Interceptor {

    private var token by defaultMMKV().string(defaultValue = "")

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newBuilder = originalRequest.newBuilder()
        newBuilder.addHeader(TOKEN_KEY, token!!)
        return chain.proceed(newBuilder.build())
    }
}
package com.jetpack.mvvm.frame.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope

/**
 * @Author: WangXing
 * @CreateDate: 2022-06-22
 * @Email: wangxing1@gwm.cn
 */
@JsonClass(generateAdapter = true)
data class ApiResponse<T>(
    @Json(name = "success") var success: Boolean,
    @Json(name = "errorCode") var errorCode: String,
    @Json(name = "errorMessage") var errorMessage: String?,
    @Json(name = "data") var data: T?
)
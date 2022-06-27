package com.jetpack.mvvm.frame.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @Author: WangXing
 * @CreateDate: 2022-06-22
 * @Email: wangxing1@gwm.cn
 */
@JsonClass(generateAdapter = true)
data class ApiResponse<T>(
    @Json(name = "code") var code: String,
    @Json(name = "msg") var errorMessage: String,
    @Json(name = "data") var data: T?
) {
    fun convertResult() = if (code == "00000") {
        Result.Success(data)
    } else {
        Result.Error(ApiError(code, errorMessage))
    }
}
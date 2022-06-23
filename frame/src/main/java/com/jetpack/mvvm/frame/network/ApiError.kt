package com.jetpack.mvvm.frame.network

/**
 * @Author: WangXing
 * @CreateDate: 2022-06-22
 * @Email: wangxing1@gwm.cn
 */
class ApiError(
    val code: String,
    message: String?
) : Exception(message) {
    fun message(): String {
        code.let {
            return "code : $it \n$message"
        }
        return message ?: "system error"
    }
}
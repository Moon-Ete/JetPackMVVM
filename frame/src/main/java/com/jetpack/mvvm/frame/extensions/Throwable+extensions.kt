package com.jetpack.mvvm.frame.extensions

import android.net.ParseException
import android.util.MalformedJsonException
import com.google.gson.JsonParseException
import com.jetpack.mvvm.frame.network.ApiError
import org.apache.http.conn.ConnectTimeoutException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLException


/**
 * @Author: WangXing
 * @CreateDate: 2022-06-22
 * @Email: wangxing1@gwm.cn
 */

/**
 * 未知错误
 */
const val UNKNOWN = "1000"

/**
 * 解析错误
 */
const val PARSE_ERROR = "1001"

/**
 * 网络错误
 */
const val NETWORK_ERROR = "1002"

/**
 * 协议出错
 */
const val HTTP_ERROR = "1003"

/**
 * 证书出错
 */
const val SSL_ERROR = "1005"

/**
 * 连接超时
 */
const val TIMEOUT_ERROR = "1006"

/**
 * 主机地址未知
 */
const val HOST_ERROR = "1007"

internal fun Throwable.convertApiError(): ApiError {
    val ex: ApiError
    return when (this) {
        is HttpException -> {
            val httpException: HttpException = this
            ex = ApiError(HTTP_ERROR, httpException.message ?: "协议出错")
            ex
        }
        is JsonParseException, is JSONException, is ParseException, is MalformedJsonException -> {
            ex = ApiError(PARSE_ERROR, "解析错误")
            ex
        }
        is ConnectException -> {
            ex = ApiError(NETWORK_ERROR, "网络错误")
            ex
        }
        is SSLException -> {
            ex = ApiError(SSL_ERROR, "证书出错")
            ex
        }
        is ConnectTimeoutException -> {
            ex = ApiError(TIMEOUT_ERROR, "连接超时")
            ex
        }
        is SocketTimeoutException -> {
            ex = ApiError(TIMEOUT_ERROR, "连接超时")
            ex
        }
        is UnknownHostException -> {
            ex = ApiError(HOST_ERROR, "主机地址未知")
            ex
        }
        else -> {
            ex = ApiError(UNKNOWN, "未知错误")
            ex
        }
    }
}

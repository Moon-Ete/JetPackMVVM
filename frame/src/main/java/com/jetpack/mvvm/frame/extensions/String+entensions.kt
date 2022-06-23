package com.jetpack.mvvm.frame.extensions

import com.orhanobut.logger.Logger

/**
 * @Author: WangXing
 * @CreateDate: 2022-06-23
 * @Email: wangxing1@gwm.cn
 */
fun String.logE() =
    Logger.e(this)

fun String.logD() =
    Logger.d(this)

fun String.logW() =
    Logger.w(this)

fun String.logV() =
    Logger.v(this)

fun String.logI() =
    Logger.i(this)

fun String.logWTF() =
    Logger.wtf(this)

fun String.logJson() = Logger.json(this)

fun String.logXML() = Logger.xml(this)
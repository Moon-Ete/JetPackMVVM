package com.jetpack.mvvm.frame.extensions

import com.jetpack.mvvm.frame.utils.MoshiUtil

/**
 * @Author: WangXing
 * @CreateDate: 2022-06-22
 * @Email: wangxing1@gwm.cn
 */

inline fun <reified T : Any> String.fromJson() = MoshiUtil.fromJson<T>(this)

fun Any.toJson() = MoshiUtil.toJson(this)


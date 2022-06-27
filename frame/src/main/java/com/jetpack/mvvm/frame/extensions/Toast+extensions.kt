package com.jetpack.mvvm.frame.extensions

import android.content.Context
import android.view.Gravity
import android.widget.Toast
import androidx.annotation.StringRes
import java.time.Duration

/**
 *
 *@author: Jia Hairan
 *@data: 2022/6/24
 *@Description: Toast工具类
 *
 **/

fun Context.toast(content: String, duration: Int = Toast.LENGTH_SHORT) {
    if (content.isEmpty()) return
    Toast.makeText(this, content, duration).apply {
        setGravity(Gravity.CENTER, 0, 0)
        show()
    }
}

fun Context.toast(@StringRes id: Int) {
    toast(getString(id))
}

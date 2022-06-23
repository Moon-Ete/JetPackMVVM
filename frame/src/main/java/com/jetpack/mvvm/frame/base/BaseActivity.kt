package com.jetpack.mvvm.frame.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

/**
 * @Author: WangXing
 * @CreateDate: 2022-06-20
 * @Email: wangxing1@gwm.cn
 */
abstract class BaseActivity(@LayoutRes layoutId: Int) :
    AppCompatActivity(layoutId) {

    constructor() : this(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        startObserve()
    }

    abstract fun initView()

    abstract fun startObserve()
}
package com.jetpack.mvvm.frame.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

/**
 * @Author: WangXing
 * @CreateDate: 2022-06-20
 * @Email: wangxing1@gwm.cn
 */
abstract class BaseFragment(@LayoutRes layoutId: Int) : Fragment(layoutId) {

    constructor() : this(0)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        startObserve()
    }

    abstract fun initView()

    abstract fun startObserve()
}

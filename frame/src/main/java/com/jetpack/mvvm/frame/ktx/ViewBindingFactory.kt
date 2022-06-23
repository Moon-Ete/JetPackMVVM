package com.jetpack.mvvm.frame.ktx

import android.view.LayoutInflater
import android.view.View
import androidx.databinding.ViewDataBinding
import kotlin.reflect.KClass

/**
 * @Author: WangXing
 * @CreateDate: 2022-06-21
 * @Email: wangxing1@gwm.cn
 */
interface ViewDataBindingFactory<T : ViewDataBinding> {

    fun getViewDataBindings(): T
}

@Suppress("UNCHECKED_CAST")
class DefaultInflateViewDataBindingFactory<T : ViewDataBinding>(
    layoutInflater: LayoutInflater,
    clazz: KClass<T>
) : ViewDataBindingFactory<T> {

    private val vb: T = clazz.java.getMethod("inflate", LayoutInflater::class.java)
        .invoke(null, layoutInflater) as T

    override fun getViewDataBindings(): T {
        return vb
    }
}

@Suppress("UNCHECKED_CAST")
class DefaultBindViewDataBindingFactory<T : ViewDataBinding>(view: View, clazz: KClass<T>) :
    ViewDataBindingFactory<T> {
    private val vb: T = clazz.java.getMethod("bind", View::class.java).invoke(null, view) as T

    override fun getViewDataBindings(): T {
        return vb
    }
}

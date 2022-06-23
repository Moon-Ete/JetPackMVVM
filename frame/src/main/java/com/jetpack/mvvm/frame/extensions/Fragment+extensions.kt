package com.jetpack.mvvm.frame.extensions

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.jetpack.mvvm.frame.ktx.DefaultBindViewDataBindingFactory
import com.jetpack.mvvm.frame.ktx.DefaultInflateViewDataBindingFactory
import com.jetpack.mvvm.frame.ktx.ViewDataBindingFactory
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * @Author: WangXing
 * @CreateDate: 2022-06-21
 * @Email: wangxing1@gwm.cn
 */

inline fun <reified T : ViewDataBinding> Fragment.viewBindings(
    crossinline viewBindingFactory: (View?) -> ViewDataBindingFactory<T> = {
        if (it == null) DefaultInflateViewDataBindingFactory(
            layoutInflater,
            T::class
        ) else DefaultBindViewDataBindingFactory(it, T::class)
    }
): ReadOnlyProperty<Fragment, T> = object : ReadOnlyProperty<Fragment, T> {

    private var binding: T? = null

    init {
        viewLifecycleOwnerLiveData.observe(this@viewBindings) { viewLifecycleOwner ->
            viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
                override fun onDestroy(owner: LifecycleOwner) {
                    (binding as? ViewDataBinding)?.unbind()
                    binding = null
                    viewLifecycleOwner.lifecycle.removeObserver(this)
                }
            })
        }
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        binding?.let { return it }
        if (!viewLifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            error("Should not attempt to get bindings when Fragment views are destroyed. The fragment has already called onDestroyView() at this point.")
        }
        return viewBindingFactory(thisRef.view).getViewDataBindings().also { viewBinding ->
            viewBinding.lifecycleOwner = viewLifecycleOwner
            this.binding = viewBinding
        }
    }
}

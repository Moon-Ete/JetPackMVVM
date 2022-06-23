package com.jetpack.mvvm.frame.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.jetpack.mvvm.frame.ktx.DefaultBindViewDataBindingFactory
import com.jetpack.mvvm.frame.ktx.DefaultInflateViewDataBindingFactory
import com.jetpack.mvvm.frame.ktx.ViewDataBindingFactory
import java.io.Serializable
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * @Author: WangXing
 * @CreateDate: 2022-06-21
 * @Email: wangxing1@gwm.cn
 */

inline fun <reified T : ViewDataBinding> AppCompatActivity.viewBindings(
    crossinline viewBindingFactory: (View?) -> ViewDataBindingFactory<T> = {
        if (it == null)
            DefaultInflateViewDataBindingFactory(
            layoutInflater,
            T::class
        ) else
            DefaultBindViewDataBindingFactory(it, T::class)
    }
): ReadOnlyProperty<AppCompatActivity, T> = object : ReadOnlyProperty<AppCompatActivity, T> {

    private var binding: T? = null

    init {
        lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onDestroy(owner: LifecycleOwner) {
                (binding as? ViewDataBinding)?.unbind()
                binding = null
                lifecycle.removeObserver(this)
            }
        })
    }

    override fun getValue(thisRef: AppCompatActivity, property: KProperty<*>): T {
        binding?.let { return it }
        if (!lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            error("Should not attempt to get bindings when Activity are destroyed. The Activity has already called onDestroy() at this point.")
        }
        val createBinding = viewBindingFactory(null).getViewDataBindings().also { viewDataBinding ->
            viewDataBinding.lifecycleOwner = thisRef
        }
        thisRef.setContentView(createBinding.root)
        return createBinding.also { this.binding = createBinding }
    }
}

inline fun <reified T : Activity> Activity.startKtxActivity(
    vararg values: Pair<String, Any>,
    flag: Int? = null,
    extra: Bundle? = null
) =
    startActivity(getIntent<T>(flag, extra, *values))


inline fun <reified T : Activity> Fragment.startKtxActivity(
    vararg values: Pair<String, Any>,
    flag: Int? = null,
    extra: Bundle? = null,
) =
    activity?.let {
        startActivity(it.getIntent<T>(flag, extra, *values))
    }

inline fun <reified T : Activity> Context.startKtxActivity(
    flag: Int? = null,
    extra: Bundle? = null,
    vararg values: Pair<String, Any>
) =
    startActivity(getIntent<T>(flag, extra, *values))


inline fun <reified T : Activity> Activity.startKtxActivityForResult(
    requestCode: Int,
    flag: Int? = null,
    extra: Bundle? = null,
    vararg values: Pair<String, Any>
) =
    startActivityForResult(getIntent<T>(flag, extra, *values), requestCode)


inline fun <reified T : Activity> Fragment.startKtxActivityForResult(
    requestCode: Int,
    flag: Int? = null,
    extra: Bundle? = null,
    vararg values: Pair<String, Any>
) =
    activity?.let {
        startActivityForResult(activity?.getIntent<T>(flag, extra, *values), requestCode)
    }

inline fun <reified T : Context> Context.getIntent(
    flag: Int? = null,
    extra: Bundle? = null,
    vararg pairs: Pair<String, Any>
): Intent =
    Intent(this, T::class.java).apply {
        flag?.let { setFlags(flags) }
        extra?.let { putExtras(extra) }
        pairs.forEach { pair ->
            val name = pair.first
            when (val value = pair.second) {
                is Int -> putExtra(name, value)
                is Byte -> putExtra(name, value)
                is Char -> putExtra(name, value)
                is Short -> putExtra(name, value)
                is Boolean -> putExtra(name, value)
                is Long -> putExtra(name, value)
                is Float -> putExtra(name, value)
                is Double -> putExtra(name, value)
                is String -> putExtra(name, value)
                is CharSequence -> putExtra(name, value)
                is Parcelable -> putExtra(name, value)
                is Array<*> -> putExtra(name, value)
                is ArrayList<*> -> putExtra(name, value)
                is Serializable -> putExtra(name, value)
                is BooleanArray -> putExtra(name, value)
                is ByteArray -> putExtra(name, value)
                is ShortArray -> putExtra(name, value)
                is CharArray -> putExtra(name, value)
                is IntArray -> putExtra(name, value)
                is LongArray -> putExtra(name, value)
                is FloatArray -> putExtra(name, value)
                is DoubleArray -> putExtra(name, value)
                is Bundle -> putExtra(name, value)
                is Intent -> putExtra(name, value)
                else -> {
                }
            }
        }
    }

fun Activity.hideKeyboard() {
    inputMethodManager?.hideSoftInputFromWindow((currentFocus ?: View(this)).windowToken, 0)
    window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    currentFocus?.clearFocus()
}

fun Activity.showKeyboard(et: EditText) {
    et.requestFocus()
    inputMethodManager?.showSoftInput(et, InputMethodManager.SHOW_IMPLICIT)
}

fun Activity.hideKeyboard(view: View) {
    inputMethodManager?.hideSoftInputFromWindow(view.windowToken, 0)
}

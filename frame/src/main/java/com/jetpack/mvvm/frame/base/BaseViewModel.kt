package com.jetpack.mvvm.frame.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel

/**
 * @Author: WangXing
 * @CreateDate: 2022-06-20
 * @Email: wangxing1@gwm.cn
 */
open class BaseViewModel : ViewModel() {

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

}
package com.jetpack.mvvm.frame.example.ui

import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.jetpack.mvvm.frame.base.BaseViewModel
import com.jetpack.mvvm.frame.example.datasources.repositories.CommonRepository
import com.jetpack.mvvm.frame.extensions.requestApi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @Author: WangXing
 * @CreateDate: 2022-06-22
 * @Email: wangxing1@gwm.cn
 */
@HiltViewModel
class MainActivityViewModel @Inject constructor(private val commonRepository: CommonRepository) :
    BaseViewModel() {

    val text = ObservableField("1222")

    fun api() {
        viewModelScope.requestApi {
            api {
                commonRepository.requestApi()
            }
            onStart {

            }
            onSuccess { data ->

            }
            onFailed { code, message ->

            }
            onComplete {

            }
        }
    }
}
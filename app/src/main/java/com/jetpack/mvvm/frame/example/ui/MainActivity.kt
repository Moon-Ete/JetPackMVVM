package com.jetpack.mvvm.frame.example.ui

import androidx.activity.viewModels
import com.jetpack.mvvm.frame.base.BaseActivity
import com.jetpack.mvvm.frame.example.databinding.ActivityMainBinding
import com.jetpack.mvvm.frame.extensions.logE
import com.jetpack.mvvm.frame.extensions.viewBindings
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val binding by viewBindings<ActivityMainBinding>()

    private val viewModel by viewModels<MainActivityViewModel>()

    override fun initView() {
        binding.apply {
            vm = viewModel
        }
    }

    override fun startObserve() {
    }
}
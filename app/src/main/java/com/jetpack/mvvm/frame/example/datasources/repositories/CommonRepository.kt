package com.jetpack.mvvm.frame.example.datasources.repositories

import com.jetpack.mvvm.frame.example.datasources.services.CommonService
import javax.inject.Inject

/**
 * @Author: WangXing
 * @CreateDate: 2022-06-22
 * @Email: wangxing1@gwm.cn
 */
class CommonRepository @Inject constructor(private val commonService: CommonService) {

    suspend fun requestApi() = commonService.getApi()

}
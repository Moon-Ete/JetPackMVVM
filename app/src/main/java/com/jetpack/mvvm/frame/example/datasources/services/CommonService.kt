package com.jetpack.mvvm.frame.example.datasources.services

import com.jetpack.mvvm.frame.network.ApiResponse
import retrofit2.http.GET

/**
 * @Author: WangXing
 * @CreateDate: 2022-06-22
 * @Email: wangxing1@gwm.cn
 */
interface CommonService {

    @GET("/")
    suspend fun getApi(): ApiResponse<String>

}
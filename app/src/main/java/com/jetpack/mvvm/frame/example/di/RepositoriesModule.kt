package com.jetpack.mvvm.frame.example.di

import com.jetpack.mvvm.frame.example.datasources.repositories.CommonRepository
import com.jetpack.mvvm.frame.example.datasources.services.CommonService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

/**
 * @Author: WangXing
 * @CreateDate: 2022-06-22
 * @Email: wangxing1@gwm.cn
 */
@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoriesModule {
    @Provides
    @ActivityRetainedScoped
    fun provideCommonRepository(
        commonService: CommonService
    ): CommonRepository {
        return CommonRepository(commonService)
    }
}
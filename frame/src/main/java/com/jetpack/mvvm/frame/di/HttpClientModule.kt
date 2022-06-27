package com.jetpack.mvvm.frame.di

import android.util.Log.VERBOSE
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.jetpack.mvvm.frame.BuildConfig
import com.jetpack.mvvm.frame.network.UnifiedHeaderInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.time.Duration
import javax.inject.Singleton

/**
 * @Author: WangXing
 * @CreateDate: 2022-06-21
 * @Email: wangxing1@gwm.cn
 */
@Module
@InstallIn(SingletonComponent::class)
object HttpClientModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(UnifiedHeaderInterceptor())
            .addInterceptor(
                LoggingInterceptor.Builder()
                    .setLevel(if (BuildConfig.DEBUG) Level.BASIC else Level.NONE)
                    .log(VERBOSE)
                    .build()
            )
            .connectTimeout(Duration.ofSeconds(10L))
            .readTimeout(Duration.ofSeconds(10L))
            .writeTimeout(Duration.ofSeconds(10L))
            .callTimeout(Duration.ofSeconds(10L))
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
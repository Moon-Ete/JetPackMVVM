package com.jetpack.mvvm.frame.initializer

import android.content.Context
import androidx.startup.Initializer
import com.jeremyliao.liveeventbus.LiveEventBus
import com.jetpack.mvvm.frame.BuildConfig
import com.orhanobut.logger.*
import com.tencent.mmkv.MMKV


/**
 * @Author: WangXing
 * @CreateDate: 2022-06-23
 * @Email: wangxing1@gwm.cn
 */
class AppInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        Logger.addLogAdapter(
            AndroidLogAdapter(
                PrettyFormatStrategy.newBuilder()
                    .tag("My custom tag")
                    .build()
            )
        )
        Logger.addLogAdapter(
            DiskLogAdapter(
                CsvFormatStrategy.newBuilder().diskPath(context.filesDir.absolutePath).build()
            )
        )
        LiveEventBus
            .config()
            .autoClear(true)
            .enableLogger(BuildConfig.DEBUG)
            .lifecycleObserverAlwaysActive(true)
        MMKV.initialize(context)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}

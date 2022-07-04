package com.jetpack.mvvm.frame.utils

import android.app.Activity
import android.app.Application
import android.content.ComponentCallbacks
import android.content.res.Configuration
import android.os.Bundle
import android.util.DisplayMetrics

/**
 * @Author: WangXing
 * @CreateDate: 2022-07-04
 * @Email: wangxing1@gwm.cn
 */

private const val WIDTH = 375F

private var appDensity = 0f

private var appScaleDensity = 0f

interface ScreenAdaptationUtil : Application.ActivityLifecycleCallbacks {
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        val displayMetrics: DisplayMetrics = activity.application.resources.displayMetrics
        if (appDensity == 0F) {
            appDensity = displayMetrics.density
            appScaleDensity = displayMetrics.scaledDensity
            activity.application.registerComponentCallbacks(object : ComponentCallbacks {
                override fun onConfigurationChanged(newConfig: Configuration) {
                    if (newConfig.fontScale > 0) {
                        appScaleDensity =
                            activity.application.resources.displayMetrics.scaledDensity
                    }
                }

                override fun onLowMemory() {
                }
            })
        }
        val targetDensity: Float = displayMetrics.widthPixels / WIDTH
        val targetScaleDensity: Float = targetDensity * (appScaleDensity / appDensity)
        val targetDensityDpi = (targetDensity * 160).toInt()
        val dm: DisplayMetrics = activity.resources.displayMetrics
        dm.density = targetDensity
        dm.scaledDensity = targetScaleDensity
        dm.densityDpi = targetDensityDpi
    }

    override fun onActivityStarted(activity: Activity) {
    }

    override fun onActivityResumed(activity: Activity) {
    }

    override fun onActivityPaused(activity: Activity) {
    }

    override fun onActivityStopped(activity: Activity) {
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }

    override fun onActivityDestroyed(activity: Activity) {
    }
}
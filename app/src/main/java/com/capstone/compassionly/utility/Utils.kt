package com.capstone.compassionly.utility

import android.app.Activity
import android.os.Build
import android.view.View
import androidx.core.view.WindowCompat

object Utils {
    fun changeStatusBarColorWhite(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowInsetsController = WindowCompat.getInsetsController(activity.window, activity.window.decorView)
            windowInsetsController.isAppearanceLightStatusBars = false
        } else {
            @Suppress("DEPRECATION")
            activity.window.decorView.systemUiVisibility = activity.window.decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }
}
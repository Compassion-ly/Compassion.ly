package com.capstone.compassionly.utility

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Build
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import com.capstone.compassionly.R
import com.capstone.compassionly.databinding.LayoutDialogRateTopicBinding

object Utils {
    fun changeStatusBarColorWhite(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowInsetsController =
                WindowCompat.getInsetsController(activity.window, activity.window.decorView)
            windowInsetsController.isAppearanceLightStatusBars = false
        } else {
            @Suppress("DEPRECATION")
            activity.window.decorView.systemUiVisibility =
                activity.window.decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }

    fun dialogInstance(applicationContext: Context): Dialog {
        val dialog = Dialog(applicationContext, R.style.MaterialDialogSheet)

        dialog.window?.apply {
            setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            setBackgroundDrawable(
                ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.rectangle_custom_white_lefttop_righttop_20dp
                )
            )
            attributes.gravity = Gravity.BOTTOM
        }

        dialog.apply {
            setCancelable(false)
            setContentView(LayoutDialogRateTopicBinding.inflate(layoutInflater).root)
        }

        return dialog
    }

}
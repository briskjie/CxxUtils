package com.briskjie.cxx.utils

import android.app.Activity
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

object CommonUtil {
    fun setNoTitlebar(activity: AppCompatActivity) {
//        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        activity.supportActionBar?.hide()
    }

    fun setFullscreen(activity: Activity) {
        activity.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    fun clearFullsreen(activity: Activity) {
        activity.window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }
}
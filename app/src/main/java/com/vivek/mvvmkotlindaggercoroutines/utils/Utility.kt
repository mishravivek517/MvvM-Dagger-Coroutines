package com.vivek.mvvmkotlindaggercoroutines.utils

import android.app.Activity
import android.view.WindowManager

class Utility {

    companion object {
        fun makeFullScreen(activity: Activity) {
            activity.window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )

        }
    }



}
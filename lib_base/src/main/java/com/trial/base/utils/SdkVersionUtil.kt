package com.trial.base.utils

import android.os.Build

/**
 * <pre>
 * @author : Trial
 * @time   : 2/4/21
 * @desc   :
 * @version: 1.0
</pre> *
 */
object SdkVersionUtil {
    fun over_19(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
    }

    fun over_21(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
    }

    fun over_23(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
    }

    fun over_24(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
    }

    fun over_25(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1
    }

    fun over_26(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
    }

    fun over_29(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
    }
}
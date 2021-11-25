package com.trial.base.constant

import android.os.Build
import com.trial.base.base.BaseApplication

/**
 * <pre>
 * @author : Trial
 * @time   : 2021/07/09
 * @desc   :
 * @version: 1.0
</pre> *
 */
object ConstantsUtils {
    /**
     * 日志缓存有效期
     */
    const val MAX_TIME = 1000 * 60 * 60 * 24 * 7

    @JvmStatic
    val logHeader: String
        get() {
            var versionName = ""
            var versionCode = 0
            try {
                val pi = BaseApplication.instance.packageManager.getPackageInfo(
                    BaseApplication.instance.packageName,
                    0
                )
                if (pi != null) {
                    versionName = pi.versionName
                    versionCode = pi.versionCode
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return """
                 ***************** Log Head *****************
                 Device Manufacturer: ${Build.MANUFACTURER}
                 Device Model       : ${Build.MODEL}
                 Android Version    : ${Build.VERSION.RELEASE}
                 Android SDK        : ${Build.VERSION.SDK_INT}
                 App VersionName    : $versionName
                 App VersionCode    : $versionCode
                 ***************** Log Head *****************
                 
                 
                 """.trimIndent()
        }
}
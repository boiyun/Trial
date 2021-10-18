package com.trial.base.constant

import android.os.Environment
import com.trial.base.utils.Utils
import java.io.File

/**
 * <pre>
 * @author : Trial
 * @time   : 2/2/21
 * @desc   :
 * @version: 1.0
</pre> *
 */
object PathConstants {
    /**
     * 日志目录
     */
    const val PATH_NAME_MOOR_LOG = "Trail_WanX"


    @JvmStatic
    fun getStoragePath(directoryName: String): String {
        var directoryDir = ""
        if (Utils.getApp() != null) {
            directoryDir = Utils.getApp().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
                .toString() + File.separator + directoryName + File.separator
        }
        return directoryDir
    }
}
package com.trial.base.utils

import com.elvishew.xlog.XLog

/**
 * <pre>
 * @author : Trial
 * @time   : 2021/11/23
 * @desc   :
 * @version: 1.0
</pre> *
 */
object TLogUtil {

    fun d(tag: String = "Trial", content: String?) {
        XLog.tag(tag).d(content)
    }

    fun d(content: String?) {
        XLog.d(content)
    }

    fun e(tag: String = "Trial", content: String?) {
        XLog.tag(tag).e(content)
    }

    fun e(content: String?) {
        XLog.e(content)
    }
}
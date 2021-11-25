package com.trial.base.utils

import com.elvishew.xlog.XLog

/**
 * <pre>
 * @author : Trial
 * @time   : 11/25/21
 * @desc   :
 * @version: 1.0
</pre> *
 */
object LogUtils {
    fun v(msg: String?) {
        XLog.v(msg)
    }

    fun v(tag: String?, msg: String?) {
        XLog.tag(tag).v(msg)
    }

    fun i(msg: String?) {
        XLog.i(msg)
    }

    fun i(tag: String?, msg: String?) {
        XLog.tag(tag).i(msg)
    }

    fun d(msg: String?) {
        XLog.d(msg)
    }

    fun d(tag: String?, msg: String?) {
        XLog.tag(tag).d(msg)
    }

    fun e(msg: String?) {
        XLog.e(msg)
    }

    fun e(tag: String?, msg: String?) {
        XLog.tag(tag).e(msg)
    }

    fun json(msg: String?) {
        XLog.json(msg)
    }
}
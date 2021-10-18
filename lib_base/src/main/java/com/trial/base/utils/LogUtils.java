package com.trial.base.utils;


import com.elvishew.xlog.XLog;

/**
 * <pre>
 *     @author : Trial
 *     @time   : 2021/07/09
 *     @desc   :
 *     @version: 1.0
 * </pre>
 */
public class LogUtils {

    public static void v(String msg) {
        XLog.v(msg);
    }

    public static void v(String tag, String msg) {
        XLog.v(tag, msg);
    }

    public static void i(String msg) {
        XLog.i(msg);
    }

    public static void i(String tag, String msg) {
        XLog.i(tag, msg);
    }

    public static void d(String msg) {
        XLog.d(msg);
    }

    public static void d(String tag, String msg) {
        XLog.d(tag, msg);
    }

    public static void e(String msg) {
        XLog.e(msg);
    }

    public static void e(String tag, String msg) {
        XLog.e(tag, msg);
    }

    public static void json(String msg) {
        XLog.json(msg);
    }

}

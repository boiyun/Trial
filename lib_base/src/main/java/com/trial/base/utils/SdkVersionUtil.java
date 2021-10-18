package com.trial.base.utils;

import android.os.Build;

/**
 * <pre>
 *     @author : Trial
 *     @time   : 2/4/21
 *     @desc   :
 *     @version: 1.0
 * </pre>
 */
public class SdkVersionUtil {

    public static boolean over_19() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    public static boolean over_21() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    public static boolean over_23() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    public static boolean over_24() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N;
    }

    public static boolean over_25() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1;
    }

    public static boolean over_26() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O;
    }

    public static boolean over_29() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q;
    }
}
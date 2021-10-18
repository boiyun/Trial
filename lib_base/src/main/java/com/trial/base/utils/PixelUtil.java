package com.trial.base.utils;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * <pre>
 *     @author : Trial
 *     @time   : 7/7/21
 *     @desc   :
 *     @version: 1.0
 * </pre>
 */
public class PixelUtil {
    public static int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

    public static int px2dp(float px) {
        float scale = Utils.getApp().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }
}

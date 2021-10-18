package com.trial.base.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * <pre>
 *     @author : Trial
 *     @time   : 7/7/21
 *     @desc   :
 *     @version: 1.0
 * </pre>
 */
public class UIUtils {
    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @return
     */
    public static int sp2px(Context context,float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int getColor(Context context,int colorId){
        return context.getResources().getColor(colorId);
    }

    public static Drawable getDrawable(Context context,int resId){
        return  context.getResources().getDrawable(resId);
    }
}

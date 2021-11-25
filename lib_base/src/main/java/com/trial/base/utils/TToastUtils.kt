package com.trial.base.utils

import com.drake.net.utils.TipUtils.toast
import com.drake.tooltip.longToast


/**
 * <pre>
 *     @author : Trial
 *     @time   : 11/23/21
 *     @desc   : ToastUtils的包装
 *     @version: 1.0
 * </pre>
 */
object TToastUtils {

    fun show(string: String) {
        if (string.isNotEmpty()) {
            toast(string)
        }
    } fun showLong(string: String) {
        if (string.isNotEmpty()) {
            longToast(string)
        }
    }

}
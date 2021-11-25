package com.trial.base.utils

import com.drake.serialize.serialize.serial
import com.drake.serialize.serialize.serialLazy

/**
 * <pre>
 *     @author : Trial
 *     @time   : 2021/11/24
 *     @desc   :
 *     @version: 1.0
 * </pre>
 */
object TSPUtil {
    //是否登录
    var loginState:Boolean by serial(false)
    //userId
    var userId:String? by serialLazy() // 懒加载
    //token
    var token:String? by serialLazy() // 懒加载
}
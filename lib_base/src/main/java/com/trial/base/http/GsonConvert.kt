package com.trial.base.http

import com.drake.net.convert.JSONConvert
import com.google.gson.GsonBuilder
import org.json.JSONObject
import java.lang.reflect.Type

/**
 * <pre>
 *     @author : Trial
 *     @time   : 2021/10/12
 *     @desc   :
 *     @version: 1.0
 * </pre>
 */
class GsonConvert : JSONConvert(code = "code", message = "msg", success = "1") {
    val gson = GsonBuilder().serializeNulls().create()

    override fun <S> String.parseBody(succeed: Type): S? {
        val data = JSONObject(this).getString("data")
        return gson.fromJson(data, succeed)
    }
}
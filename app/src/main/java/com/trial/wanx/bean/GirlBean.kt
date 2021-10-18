package com.trial.wanx.bean
import com.google.gson.annotations.SerializedName


/**
 * <pre>
 *     @author : Trial
 *     @time   : 2021/10/13
 *     @desc   :
 *     @version: 1.0
 * </pre>
 */
data class GirlBean(
    @SerializedName("imageFileLength")
    var imageFileLength: Int,
    @SerializedName("imageSize")
    var imageSize: String,
    @SerializedName("imageUrl")
    var imageUrl: String,
    @SerializedName("content")
    var content: String,
    @SerializedName("updateTime")
    var updateTime: String
)
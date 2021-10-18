package com.trial.wanx.bean
import com.google.gson.annotations.SerializedName


/**
 * <pre>
 *     @author : Trial
 *     @time   : 2021/10/14
 *     @desc   :
 *     @version: 1.0
 * </pre>
 */
data class NewsBean(
    @SerializedName("digest")
    var digest: String,
    @SerializedName("imgList")
    var imgList: List<String>,
    @SerializedName("newsId")
    var newsId: String,
    @SerializedName("postTime")
    var postTime: String,
    @SerializedName("source")
    var source: String,
    @SerializedName("title")
    var title: String,
    @SerializedName("typeId")
    var typeId: String,
    @SerializedName("typeName")
    var typeName: String

)
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
data class NewsDetail(
    @SerializedName("content")
    var content: String,
    @SerializedName("cover")
    var cover: String,
    @SerializedName("docid")
    var docid: String,
    @SerializedName("images")
    var images: List<Image>,
    @SerializedName("ptime")
    var ptime: String,
    @SerializedName("source")
    var source: String,
    @SerializedName("title")
    var title: String
) {
    data class Image(
        @SerializedName("imgSrc")
        var imgSrc: String,
        @SerializedName("position")
        var position: String,
        @SerializedName("size")
        var size: String
    )
}
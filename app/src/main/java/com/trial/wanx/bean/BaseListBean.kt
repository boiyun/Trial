package com.trial.wanx.bean

import com.google.gson.annotations.SerializedName


/**
 * <pre>
 * @author : Trial
 * @time   : 7/7/21
 * @desc   :
 * @version: 1.0
</pre> *
 */
data class BaseListBean(
    @SerializedName("limit")
    var limit: Int,
    @SerializedName("list")
    var list: List<GirlBean>,
    @SerializedName("page")
    var page: Int,
    @SerializedName("totalCount")
    var totalCount: Int,
    @SerializedName("totalPage")
    var totalPage: Int
)
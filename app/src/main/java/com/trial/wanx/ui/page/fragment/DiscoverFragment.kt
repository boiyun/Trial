package com.trial.wanx.ui.page.fragment

import android.os.Bundle
import com.drake.brv.utils.divider
import com.drake.brv.utils.linear
import com.drake.brv.utils.setup
import com.drake.net.Get
import com.drake.net.utils.scope
import com.drake.statusbar.statusPadding
import com.trial.base.base.BaseFragment
import com.trial.wanx.R
import com.trial.wanx.bean.BaseListBean
import com.trial.wanx.bean.GirlBean
import com.trial.wanx.constant.UrlManager
import com.trial.wanx.databinding.FragmentDiscoverBinding

/**
 * <pre>
 * @author : Trial
 * @time   : 7/7/21
 * @desc   :
 * @version: 1.0
</pre> *
 */
class DiscoverFragment : BaseFragment<FragmentDiscoverBinding>(R.layout.fragment_discover) {

    companion object {
        fun newInstance(): DiscoverFragment {
            val fragment = DiscoverFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun initView() {
        binding.rv.linear()
            .divider(R.drawable.divider_horizontal)
            .setup {
                addType<GirlBean>(R.layout.item_jokes_list)
            }
    }

    override fun initData() {
        binding.pageRefresh.onRefresh {
            scope {
                val await = Get<BaseListBean>(UrlManager.JOKES_LIST_URL) {
                    param("page", index)
                }.await()
                val data = await.list
                addData(data,
                    hasMore = { index < await.totalPage },
                    isEmpty = { data.isEmpty() })
            }
        }.showLoading()
    }
}
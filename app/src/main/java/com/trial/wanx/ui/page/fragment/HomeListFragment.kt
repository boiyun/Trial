package com.trial.wanx.ui.page.fragment

import android.os.Bundle
import com.drake.brv.utils.divider
import com.drake.brv.utils.linear
import com.drake.brv.utils.setup
import com.drake.net.Get
import com.drake.net.utils.scope
import com.drake.serialize.intent.bundle
import com.drake.serialize.intent.openActivity
import com.trial.base.base.BaseFragment
import com.trial.wanx.R
import com.trial.wanx.bean.NewsBean
import com.trial.wanx.constant.UrlManager
import com.trial.wanx.databinding.FragmentHomeListBinding
import com.trial.wanx.ui.page.activity.NewsDetailActivity


/**
 * <pre>
 * @author : Trial
 * @time   : 8/11/21
 * @desc   :
 * @version: 1.0
</pre> *
 */
class HomeListFragment : BaseFragment<FragmentHomeListBinding>(R.layout.fragment_home_list) {
    private val typeId: String by bundle()

    companion object {
        fun newInstance(typeId: String): HomeListFragment {
            val fragment = HomeListFragment()
            val args = Bundle()
            args.putString("typeId", typeId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun initView() {
        binding.rv.linear()
            .divider(R.drawable.divider_horizontal)
            .setup {
                addType<NewsBean>(R.layout.item_news_list)
                onClick(R.id.item_container) {
                    val model = getModel<NewsBean>()
                    if (model.newsId == "此类型无详情id") {
                        return@onClick
                    }
                    openActivity<NewsDetailActivity>(
                        "imgUrl" to model.imgList[0],
                        "newsId" to model.newsId
                    )
                }
            }
        binding.pageRefresh.onRefresh {
            scope {
                val data = Get<List<NewsBean>>(UrlManager.NEWS_LIST_URL) {
                    param("page", index)
                    param("typeId", typeId)
                }.await()
                addData(data, isEmpty = { data.isEmpty() })
            }
        }.showLoading()
    }


    override fun initData() {
    }

}
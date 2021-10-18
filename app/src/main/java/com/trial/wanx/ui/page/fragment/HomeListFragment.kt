package com.trial.wanx.ui.page.fragment

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import com.drake.brv.utils.divider
import com.drake.brv.utils.linear
import com.drake.brv.utils.setup
import com.drake.net.Get
import com.drake.net.utils.scope
import com.drake.serialize.intent.bundle
import com.skydoves.transformationlayout.TransformationCompat
import com.skydoves.transformationlayout.TransformationLayout
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
                var previousTime = SystemClock.elapsedRealtime()
                addType<NewsBean>(R.layout.item_news_list)
                onClick(R.id.transformationLayout) {
                    val model = getModel<NewsBean>()
                    if (model.newsId == "此类型无详情id") {
                        return@onClick
                    }
                    val now = SystemClock.elapsedRealtime()
                    val transformationLayout =
                        findView<TransformationLayout>(R.id.transformationLayout)
                    if (now - previousTime >= transformationLayout.duration) {
                        val intent = Intent(context, NewsDetailActivity::class.java)

                        intent.putExtra("newsId", model.newsId)
                        if (model.imgList.isNullOrEmpty()) {
                            intent.putExtra("imgUrl", "")
                        } else {
                            intent.putExtra("imgUrl", model.imgList[0])
                        }

                        TransformationCompat.startActivity(
                            transformationLayout,
                            intent
                        )
                        previousTime = now
                    }
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
        }
    }


    override fun initData() {
        binding.pageRefresh.showLoading()
    }

}
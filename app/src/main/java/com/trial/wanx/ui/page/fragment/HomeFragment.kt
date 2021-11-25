package com.trial.wanx.ui.page.fragment

import android.content.Context
import android.os.Bundle
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.drake.net.Get
import com.drake.net.utils.scopeNetLife
import com.drake.statusbar.statusPadding
import com.trial.base.adapter.VPAdapter
import com.trial.base.base.BaseFragment
import com.trial.wanx.R
import com.trial.wanx.bean.NewsBean
import com.trial.wanx.constant.UrlManager
import com.trial.wanx.databinding.FragmentHomeBinding
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.UIUtil
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView


/**
 * <pre>
 * @author : Trial
 * @time   : 8/11/21
 * @desc   :
 * @version: 1.0
</pre> *
 */
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private lateinit var mTitleDataList: MutableList<String>
    private lateinit var mFragmentList: MutableList<Fragment>

    companion object {
        fun newInstance(): HomeFragment {
            val fragment = HomeFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun initView() {
    }

    override fun initData() {
        mTitleDataList = mutableListOf()
        mFragmentList = mutableListOf()
        scopeNetLife {
            val await = Get<List<NewsBean>>(UrlManager.NEWS_TYPE_URL).await()
            await.forEach {
                mTitleDataList.add(it.typeName)
                mFragmentList.add(HomeListFragment.newInstance(it.typeId))
            }
            if (mFragmentList.isNotEmpty()) {
                val vpAdapter = VPAdapter(childFragmentManager, mFragmentList)
                binding.vpHome.run {
                    adapter = vpAdapter
                    offscreenPageLimit = await.size
                    currentItem = 0
                }
            }
            if (mTitleDataList.isNotEmpty()) {
                initIndicator()
            }
        }
    }

    private fun initIndicator() {
        val commonNavigator = CommonNavigator(activity)
        commonNavigator.adapter = object : CommonNavigatorAdapter() {
            override fun getCount(): Int {
                return mTitleDataList.size
            }

            override fun getTitleView(context: Context, index: Int): IPagerTitleView {
                val colorTransitionPagerTitleView = ColorTransitionPagerTitleView(context)
                colorTransitionPagerTitleView.normalColor =
                    ContextCompat.getColor(activity!!, R.color.color_666666)
                colorTransitionPagerTitleView.selectedColor =
                    ContextCompat.getColor(activity!!, R.color.color_ff9100)
                colorTransitionPagerTitleView.text = mTitleDataList[index]
                colorTransitionPagerTitleView.setOnClickListener {
                    binding.vpHome.currentItem = index
                }

                return colorTransitionPagerTitleView
            }

            override fun getIndicator(context: Context): IPagerIndicator {
                return LinePagerIndicator(context).apply {
                    mode = LinePagerIndicator.MODE_EXACTLY
                    lineHeight = UIUtil.dip2px(context, 3.0).toFloat()
                    lineWidth = UIUtil.dip2px(context, 20.0).toFloat()
                    roundRadius = UIUtil.dip2px(context, 3.0).toFloat()
                    startInterpolator = AccelerateInterpolator()
                    endInterpolator = DecelerateInterpolator(2.0f)
                    setColors(activity?.resources?.getColor(R.color.color_ff9100))
                }
            }
        }
        binding.tabHome.navigator = commonNavigator
        ViewPagerHelper.bind(binding.tabHome, binding.vpHome);

    }

}
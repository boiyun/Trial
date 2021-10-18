package com.trial.wanx.ui.page

import android.app.SharedElementCallback
import android.graphics.Matrix
import android.graphics.RectF
import android.os.Parcelable
import android.view.View
import androidx.fragment.app.Fragment
import com.trial.base.adapter.MainPageAdapter
import com.trial.base.base.BaseActivity
import com.trial.base.widget.bottombar.NavigationController
import com.trial.wanx.R
import com.trial.wanx.databinding.ActivityMainBinding
import com.trial.wanx.ui.page.fragment.DiscoverFragment
import com.trial.wanx.ui.page.fragment.GirlFragment
import com.trial.wanx.ui.page.fragment.HomeFragment
import com.trial.wanx.ui.page.fragment.HomeListFragment

/**
 * <pre>
 *     @author : Trial
 *     @time   : 9/30/21
 *     @desc   :
 *     @version: 1.0
 * </pre>
 */
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private lateinit var fragmentList: MutableList<Fragment>

    override fun initView() {
        val homeFragment = HomeFragment.newInstance()
        val girlFragment = GirlFragment.newInstance()
        val discoverFragment = DiscoverFragment.newInstance()
        fragmentList = mutableListOf(
            homeFragment,
            girlFragment,
            discoverFragment
        )
        binding.vpContent.adapter = MainPageAdapter(supportFragmentManager, fragmentList)
        binding.vpContent.offscreenPageLimit = 3
        val navigationController: NavigationController = binding.navTab.material()
            .addItem(
                R.drawable.icon_tab_home,
                R.drawable.icon_tab_home,
                "首页",
                resources.getColor(R.color.color_ff9100)
            )
            .addItem(
                R.drawable.icon_tab_pic,
                R.drawable.icon_tab_pic,
                "福利",
                resources.getColor(R.color.color_5082e1)
            )
            .addItem(
                R.drawable.icon_tab_discover,
                R.drawable.icon_tab_discover,
                "段子",
                resources.getColor(R.color.color_2bb24c)
            )
            .build()

        navigationController.setupWithViewPager(binding.vpContent)

    }

    override fun finishAfterTransition() {
        super.finish()
    }

    override fun initData() {
        //返回页面时图片闪烁问题
        setExitSharedElementCallback(object : SharedElementCallback() {
            override fun onCaptureSharedElementSnapshot(
                sharedElement: View,
                viewToGlobalMatrix: Matrix,
                screenBounds: RectF
            ): Parcelable {
                sharedElement.alpha = 1f
                return super.onCaptureSharedElementSnapshot(
                    sharedElement,
                    viewToGlobalMatrix,
                    screenBounds
                )
            }
        })
    }
}
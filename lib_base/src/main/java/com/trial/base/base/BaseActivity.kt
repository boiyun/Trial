package com.trial.base.base

import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.trial.base.R
import com.zackratos.ultimatebarx.ultimatebarx.statusBar

/**
 * <pre>
 * @author : Trial
 * @time   : 7/7/21
 * @desc   :
 * @version: 1.0
</pre> *
 */
abstract class BaseActivity<B : ViewDataBinding>(@LayoutRes contentLayoutId: Int = 0) :
    AppCompatActivity(contentLayoutId) {
    var mContext: AppCompatActivity? = null
    lateinit var binding: B
    lateinit var rootView: View
    private var mActivityProvider: ViewModelProvider? = null
    override fun setContentView(layoutResId: Int) {
        rootView = layoutInflater.inflate(layoutResId, null)
        setContentView(rootView)
        mContext = this
        // 设置状态栏
        statusBar {
            // 布局是否侵入状态栏（true 不侵入，false 侵入）
            fitWindow = false
            // 状态栏背景颜色（资源 id）
            colorRes = R.color.color_eaeaea
            light = false
            lvlColorRes = R.color.color_eaeaea
        }
        binding = DataBindingUtil.bind(rootView)!!
        initView()
        initData()
    }

    protected abstract fun initView()

    protected abstract fun initData()

    protected fun <T : ViewModel?> getActivityScopeViewModel(modelClass: Class<T>): T {
        if (mActivityProvider == null) {
            mActivityProvider = ViewModelProvider(this)
        }
        return mActivityProvider!![modelClass]
    }
}
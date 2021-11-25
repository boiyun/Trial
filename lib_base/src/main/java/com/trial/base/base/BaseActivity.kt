package com.trial.base.base

import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.drake.statusbar.immersive
import com.trial.base.R

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
        immersive(resources.getColor(R.color.main_color),true)
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
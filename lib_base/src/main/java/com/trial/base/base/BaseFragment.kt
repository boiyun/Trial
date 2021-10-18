package com.trial.base.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


/**
 * <pre>
 * @author : Trial
 * @time   : 7/7/21
 * @desc   :
 * @version: 1.0
</pre> *
 */
abstract class BaseFragment<B : ViewDataBinding>(@LayoutRes contentLayoutId: Int = 0) :
    Fragment(contentLayoutId) {
    lateinit var binding: B


    private var mFragmentProvider: ViewModelProvider? = null

    /**
     * Fragment的View加载完毕的标记
     */
    private var isViewCreated = false

    /**
     * Fragment对用户可见的标记
     */
    private var isDataLoaded = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = DataBindingUtil.bind(view)!!
        initView()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        prepareRequestData()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        isViewCreated = true
        prepareRequestData()

        super.onActivityCreated(savedInstanceState)
    }

    private fun prepareRequestData(forceUpdate: Boolean = false): Boolean {
        if (userVisibleHint && isViewCreated && (!isDataLoaded || forceUpdate)) {
            initData()
            isDataLoaded = true
            return true
        }
        return false
    }

    protected abstract fun initView()

    protected abstract fun initData()

    protected fun <T : ViewModel?> getFragmentScopeViewModel(modelClass: Class<T>): T {
        if (mFragmentProvider == null) {
            mFragmentProvider = ViewModelProvider(this)
        }
        return mFragmentProvider!![modelClass]
    }


}
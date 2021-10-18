package com.trial.base.base

import android.app.Application
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

/**
 * <pre>
 * @author : Trial
 * @time   : 7/7/21
 * @desc   :
 * @version: 1.0
</pre> *
 */
open class BaseApplication : Application(), ViewModelStoreOwner {
    private var mAppViewModelStore: ViewModelStore? = null
    override fun onCreate() {
        super.onCreate()
        mAppViewModelStore = ViewModelStore()
    }

    override fun getViewModelStore(): ViewModelStore {
        return mAppViewModelStore!!
    }
}
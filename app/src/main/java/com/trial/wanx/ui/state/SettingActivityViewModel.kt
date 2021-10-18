package com.trial.wanx.ui.state

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * <pre>
 * @author : Trial
 * @time   : 7/7/21
 * @desc   :
 * @version: 1.0
 *
</pre> *
 */
class SettingActivityViewModel : ViewModel() {
    /**
     * ObservableField 主要是支持 “防抖”，避免在值相同的情况下，重复刷新视图，以提升性能
     */
    val titleName = ObservableField<String>()

    /**
     * 在某些特殊的情况下，不得不改用 LiveData，以便绕过 防抖的限制，使得能够一再地通知所绑定视图的刷新。
     */
    val autoScrollToTopWhenInsert = MutableLiveData<Boolean>()
}
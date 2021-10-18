package com.trial.wanx.ui.state

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

/**
 * <pre>
 * @author : Trial
 * @time   : 7/7/21
 * @desc   :
 * @version: 1.0
</pre> *
 */
class AccountFragmentViewModel : ViewModel() {
    @JvmField
    val userName = ObservableField<String>()

    init {
        userName.set("unKnow")
    }
}
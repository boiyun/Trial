package com.trial.wanx.ui.page.fragment

import android.app.SharedElementCallback
import android.content.Intent
import android.graphics.Matrix
import android.graphics.RectF
import android.os.Bundle
import android.os.Parcelable
import android.os.SystemClock
import android.view.View
import com.drake.brv.utils.divider
import com.drake.brv.utils.grid
import com.drake.brv.utils.setup
import com.drake.net.Get
import com.drake.net.utils.scope
import com.drake.statusbar.statusPadding
import com.trial.base.base.BaseFragment
import com.trial.base.widget.transformationlayout.TransformationCompat
import com.trial.base.widget.transformationlayout.TransformationLayout
import com.trial.wanx.R
import com.trial.wanx.bean.BaseListBean
import com.trial.wanx.bean.GirlBean
import com.trial.wanx.constant.UrlManager
import com.trial.wanx.databinding.FragmentGirlBinding
import com.trial.wanx.ui.page.activity.ImageLookActivity


/**
 * <pre>
 * @author : Trial
 * @time   : 8/11/21
 * @desc   :
 * @version: 1.0
</pre> *
 */
class GirlFragment : BaseFragment<FragmentGirlBinding>(R.layout.fragment_girl) {

    companion object {
        fun newInstance(): GirlFragment {
            val fragment = GirlFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun initView() {
        binding.toolbar.statusPadding()
        binding.rv.grid(2)
            .divider(R.drawable.divider_horizontal)
            .setup {
                var previousTime = SystemClock.elapsedRealtime()
                addType<GirlBean>(R.layout.item_girl_list)
                onClick(R.id.transformationLayout) {
                    val now = SystemClock.elapsedRealtime()
                    val transformationLayout =
                        findView<TransformationLayout>(R.id.transformationLayout)
                    if (now - previousTime >= transformationLayout.duration) {
                        val intent = Intent(context, ImageLookActivity::class.java)
                        intent.putExtra("imgUrl", getModel<GirlBean>().imageUrl)

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
                val await = Get<BaseListBean>(UrlManager.GIRL_LIST_URL) {
                    param("page", index)
                }.await()
                val data = await.list
                addData(data, hasMore = { index < await.totalPage }, isEmpty = { data.isEmpty() })
            }
        }
        //返回页面时图片闪烁问题
        activity?.setExitSharedElementCallback(object : SharedElementCallback() {
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


    override fun initData() {
        binding.pageRefresh.showLoading()
    }

}
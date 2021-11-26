package com.trial.wanx.ui.page.fragment

import android.app.SharedElementCallback
import android.graphics.Matrix
import android.graphics.RectF
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.ImageView
import com.drake.brv.utils.divider
import com.drake.brv.utils.grid
import com.drake.brv.utils.setup
import com.drake.net.Get
import com.drake.net.utils.scope
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.util.SmartGlideImageLoader
import com.trial.base.base.BaseFragment
import com.trial.wanx.R
import com.trial.wanx.bean.BaseListBean
import com.trial.wanx.bean.GirlBean
import com.trial.wanx.constant.UrlManager
import com.trial.wanx.databinding.FragmentGirlBinding


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
        val imgList = arrayListOf<String>()
        binding.rv.grid(2)
            .divider(R.drawable.divider_horizontal)
            .setup {
                addType<GirlBean>(R.layout.item_girl_list)
                onClick(R.id.item_container) {

                    XPopup.Builder(activity)
                        .asImageViewer(
                            itemView.findViewById(R.id.iv_girl),
                            modelPosition,
                            imgList as List<Any>?,
                            { popupView, position ->
                                popupView.updateSrcView(
                                    (rv?.getChildAt(position)
                                        ?.findViewById(R.id.iv_girl)) as ImageView
                                )
                            },
                            SmartGlideImageLoader(true,R.drawable.icon_error)
                        )
                        .show()
                }
            }
        binding.pageRefresh.onRefresh {
            if (index==1) {
                imgList.clear()
            }
            scope {
                val await = Get<BaseListBean>(UrlManager.GIRL_LIST_URL) {
                    param("page", index)
                }.await()
                val data = await.list
                addData(data, hasMore = { index < await.totalPage }, isEmpty = { data.isEmpty() })

                data.forEach {
                    imgList.add(it.imageUrl)
                }
            }
        }.showLoading()
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
    }

}
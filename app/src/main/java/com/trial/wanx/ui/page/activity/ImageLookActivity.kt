package com.trial.wanx.ui.page.activity

import android.os.Bundle
import com.bumptech.glide.Glide
import com.drake.serialize.intent.bundle
import com.drake.statusbar.immersive
import com.github.chrisbanes.photoview.PhotoView
import com.trial.base.base.BaseActivity
import com.trial.wanx.R
import com.trial.wanx.databinding.ActivityImageLookBinding


/**
 * <pre>
 * @author : Trial
 * @time   : 7/7/21
 * @desc   :
 * @version: 1.0
</pre> *
 */
class ImageLookActivity : BaseActivity<ActivityImageLookBinding>(R.layout.activity_image_look) {
    private val imgUrl: String by bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initView() {
        immersive(darkMode = false)
        val photoview = findViewById<PhotoView>(R.id.photoview)
        Glide.with(photoview.context.applicationContext).load(imgUrl).into(photoview)
        photoview.setOnClickListener {
            supportFinishAfterTransition()
        }
    }

    override fun initData() {
    }

}
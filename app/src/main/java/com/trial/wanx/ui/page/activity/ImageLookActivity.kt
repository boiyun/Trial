package com.trial.wanx.ui.page.activity

import android.annotation.SuppressLint
import android.os.Bundle
import com.bumptech.glide.Glide
import com.drake.serialize.intent.bundle
import com.drake.statusbar.immersive
import com.github.chrisbanes.photoview.PhotoView
import com.skydoves.transformationlayout.TransformationAppCompatActivity
import com.trial.wanx.R
import com.zackratos.ultimatebarx.ultimatebarx.statusBar


/**
 * <pre>
 * @author : Trial
 * @time   : 7/7/21
 * @desc   :
 * @version: 1.0
</pre> *
 */
class ImageLookActivity : TransformationAppCompatActivity() {
    private val imgUrl: String by bundle()

    @SuppressLint("MissingSuperCall")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_look)
        immersive()
        val photoview = findViewById<PhotoView>(R.id.photoview)
        Glide.with(photoview.context.applicationContext).load(imgUrl).into(photoview)
        photoview.setOnClickListener {
            supportFinishAfterTransition()
        }
    }

}
package com.trial.wanx.ui.page.activity

import android.os.Bundle
import android.text.Html
import com.bumptech.glide.Glide
import com.drake.engine.databinding.setContent
import com.drake.net.Get
import com.drake.net.utils.scopeNetLife
import com.drake.serialize.intent.bundle
import com.drake.statusbar.immersive
import com.drake.statusbar.statusPadding
import com.skydoves.transformationlayout.TransformationAppCompatActivity
import com.trial.wanx.R
import com.trial.wanx.bean.NewsDetail
import com.trial.wanx.constant.UrlManager
import com.trial.wanx.databinding.ActivityNewsDetailBinding
import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter
import org.sufficientlysecure.htmltextview.HtmlResImageGetter


/**
 * <pre>
 * @author : Trial
 * @time   : 7/7/21
 * @desc   :
 * @version: 1.0
</pre> *
 */
class NewsDetailActivity : TransformationAppCompatActivity() {
    private val newsId: String by bundle()
    private val imgUrl: String by bundle()
    private lateinit var binding: ActivityNewsDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setContent(R.layout.activity_news_detail)
        immersive()
        initView()
        initData()
    }

    private fun initView() {
        setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationOnClickListener { supportFinishAfterTransition() }
        binding.toolbar.statusPadding()
        Glide.with(binding.ivNewsImg.context.applicationContext).load(imgUrl)
            .into(binding.ivNewsImg)

    }

    private fun initData() {
        scopeNetLife {
            val data = Get<NewsDetail>(UrlManager.NEWS_DETAILS_URL) {
                param("newsId", newsId)
            }.await()
            binding.tvTitle.text = data.title
            binding.collapsing.title = data.source

            var replace = data.content
            if (!data.images.isNullOrEmpty()) {
                data.images.forEach {
                    replace = replace.replace(it.position, "<img src=\"${it.imgSrc}\"/>")
                }
            }
            binding.tvContent.setHtml(replace, HtmlHttpImageGetter(binding.tvContent))
        }

    }
}
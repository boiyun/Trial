package com.trial.base.binding

import android.R
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.trial.base.utils.ClickUtils

/**
 * <pre>
 * @author : Trial
 * @time   : 7/7/21
 * @desc   :
 * @version: 1.0
</pre> *
 */
object CommonBindingAdapter {
    @JvmStatic
    @BindingAdapter(value = ["imageUrl", "placeHolder"], requireAll = false)
    fun loadUrl(view: ImageView, url: String?, placeHolder: Drawable?) {
        Glide.with(view.context.applicationContext).load(url).placeholder(placeHolder).into(view)
    }

    @JvmStatic
    @BindingAdapter(value = ["visible"], requireAll = false)
    fun visible(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter(value = ["showDrawable", "drawableShowed"], requireAll = false)
    fun showDrawable(view: ImageView, showDrawable: Boolean, drawableShowed: Int) {
        view.setImageResource(if (showDrawable) drawableShowed else R.color.transparent)
    }

    @JvmStatic
    @BindingAdapter(value = ["textColor"], requireAll = false)
    fun setTextColor(textView: TextView, textColorRes: Int) {
        textView.setTextColor(textView.resources.getColor(textColorRes))
    }

    @JvmStatic
    @BindingAdapter(value = ["imageRes"], requireAll = false)
    fun setImageRes(imageView: ImageView, imageRes: Int) {
        imageView.setImageResource(imageRes)
    }

    @JvmStatic
    @BindingAdapter(value = ["selected"], requireAll = false)
    fun selected(view: View, select: Boolean) {
        view.isSelected = select
    }

    @JvmStatic
    @BindingAdapter(value = ["onClickWithDebouncing"], requireAll = false)
    fun onClickWithDebouncing(view: View?, clickListener: View.OnClickListener?) {
        ClickUtils.applySingleDebouncing(view, clickListener)
    }

    @JvmStatic
    @BindingAdapter(value = ["adjustWidth"])
    fun adjustWidth(view: View, adjustWidth: Int) {
        val params = view.layoutParams
        params.width = adjustWidth
        view.layoutParams = params
    }

    @JvmStatic
    @BindingAdapter(value = ["adjustHeight"])
    fun adjustHeight(view: View, adjustHeight: Int) {
        val params = view.layoutParams
        params.height = adjustHeight
        view.layoutParams = params
    }

    @JvmStatic
    @BindingAdapter(value = ["toolbarNavigationClick"])
    fun setNavigationClick(toolbar: Toolbar, listener: View.OnClickListener?) {
        toolbar.setNavigationOnClickListener(listener)
    }

    @JvmStatic
    @BindingAdapter(value = ["toolbarMenuItemClick"])
    fun setMenuItemClick(toolbar: Toolbar, listener: Toolbar.OnMenuItemClickListener?) {
        toolbar.setOnMenuItemClickListener(listener)
    }
}
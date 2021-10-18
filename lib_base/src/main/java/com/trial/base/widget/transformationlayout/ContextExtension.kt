package com.trial.base.widget.transformationlayout

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper

/** gets an activity from a context. */
@JvmSynthetic
internal fun Context.getActivity(): Activity? {
  var context = this
  while (context is ContextWrapper) {
    if (context is Activity) {
      return context
    }
    context = context.baseContext
  }
  return null
}

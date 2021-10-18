@file:Suppress("unused")

package com.trial.base.widget.transformationlayout

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

/** Helper for accessing features in starting activities with transformation animation. */
object TransformationCompat {

  /** A common definition of the activity's transition name. */
   const val activityTransitionName: String = "com.skydoves.transformationlayout"

  /** Invalidate the activity's options menu, if able. */
  @JvmStatic
  fun startActivity(
    transformationLayout: TransformationLayout,
    intent: Intent
  ) {
    transformationLayout.startActivityWithBundleOptions(intent) { workedIntent, bundle ->
      ActivityCompat.startActivity(transformationLayout.context, workedIntent, bundle)
    }
  }

  /**
   * Start new activity with options, if able, for which you would like a
   * result when it finished.
   */
  @JvmStatic
  fun startActivityForResult(
    transformationLayout: TransformationLayout,
    intent: Intent,
    requestCode: Int
  ) {
    val activity = transformationLayout.context.getActivity()
    if (activity != null) {
      transformationLayout.startActivityWithBundleOptions(intent) { workedIntent, bundle ->
        ActivityCompat.startActivityForResult(activity, workedIntent, requestCode, bundle)
      }
    }
  }

//  /**
//   * After starts a new activity by using [startActivity] or [startActivityForResult] in [TransformationCompat],
//   * apply the [TransformationLayout.Params] on an Activity.
//   */
//  @JvmStatic
//  fun onTransformationEndContainerApplyParams(activity: Activity) {
//    onTransformationEndContainer(
//      activity.intent.getParcelableExtra(activityTransitionName)
//    )
//  }

  /** sets an exit shared element callback to activity for implementing shared element transition. */
  @JvmStatic
  fun onTransformationStartContainer(activity: Activity) {
    activity.onTransformationStartContainer()
  }

  /** sets an enter shared element callback to activity for implementing shared element transition. */
  @JvmStatic
  fun onTransformationEndContainer(activity: Activity, params: TransformationLayout.Params?) {
    activity.onTransformationEndContainer(params)
  }

  /** sets an exit shared element callback to fragment for implementing shared element transition. */
  @JvmStatic
  fun onTransformationStartContainer(fragment: Fragment) {
    fragment.onTransformationStartContainer()
  }

  /** sets an enter shared element callback to fragment for implementing shared element transition. */
  @JvmStatic
  fun onTransformationEndContainer(fragment: Fragment, params: TransformationLayout.Params?) {
    fragment.onTransformationEndContainer(params)
  }

  /** adds a shared element transformation to FragmentTransaction. */
  @JvmStatic
  fun addTransformation(
    fragmentTransaction: FragmentTransaction,
    transformationLayout: TransformationLayout,
    transitionName: String? = null
  ) {
    fragmentTransaction.addTransformation(transformationLayout, transitionName)
  }

  private inline fun TransformationLayout.startActivityWithBundleOptions(
    intent: Intent,
    block: (Intent, Bundle) -> Unit
  ) {
    val now = SystemClock.elapsedRealtime()
    if (now - throttledTime > duration) {
      throttledTime = now
      val bundle = withView(this, activityTransitionName)
      intent.putExtra(activityTransitionName, getParcelableParams())
      block(intent, bundle)
    }
  }
}

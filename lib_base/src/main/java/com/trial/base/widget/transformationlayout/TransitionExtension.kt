package com.trial.base.widget.transformationlayout

import android.app.Activity
import android.view.Window
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.transition.platform.Hold
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback

/** sets an exit shared element callback to activity for implementing shared element transition. */
fun Activity.onTransformationStartContainer() {
  window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
  setExitSharedElementCallback(MaterialContainerTransformSharedElementCallback())
  window.sharedElementsUseOverlay = false
}

/** sets an enter shared element callback to activity for implementing shared element transition. */
fun Activity.onTransformationEndContainer(
  params: TransformationLayout.Params?
) {
  requireNotNull(
    params
  ) { "TransformationLayout.Params must not be a null. check your intent key value is correct." }
  window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
  ViewCompat.setTransitionName(findViewById(android.R.id.content), params.transitionName)
  setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())
  window.sharedElementEnterTransition = params.getMaterialContainerTransform()
  window.sharedElementReturnTransition = params.getMaterialContainerTransform()
}

/** sets an exit shared element callback to fragment for implementing shared element transition. */
fun Fragment.onTransformationStartContainer() {
  exitTransition = Hold()
}

/** sets an enter shared element callback to fragment for implementing shared element transition. */
fun Fragment.onTransformationEndContainer(
  params: TransformationLayout.Params?
) {
  requireNotNull(
    params
  ) { "TransformationLayout.Params must not be a null. check your intent key value is correct." }
  sharedElementEnterTransition = params.getMaterialFragmentTransform()
}

/** adds a shared element transformation to FragmentTransaction. */
fun FragmentTransaction.addTransformation(
    transformationLayout: TransformationLayout,
    transitionName: String? = null
): FragmentTransaction {
  if (transitionName != null && transformationLayout.transitionName == null) {
    ViewCompat.setTransitionName(transformationLayout, transitionName)
  }
  addSharedElement(transformationLayout, transformationLayout.transitionName)
  return this
}

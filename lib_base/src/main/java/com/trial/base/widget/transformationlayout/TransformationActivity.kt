package com.trial.base.widget.transformationlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.trial.base.widget.transformationlayout.TransformationCompat.activityTransitionName

/** An abstract activity extending [ComponentActivity] with registering transformation automatically. */
abstract class TransformationActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    onTransformationEndContainer(
      intent.getParcelableExtra(
        activityTransitionName
      )
    )
    super.onCreate(savedInstanceState)
  }
}

package com.parsuomash.sdk.di.scope

import androidx.activity.ComponentActivity
import com.parsuomash.sdk.di.context.SdkKoinComponent
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityScope
import org.koin.core.scope.Scope

internal abstract class ActivityScope
  : ComponentActivity(), SdkKoinComponent, AndroidScopeComponent {
  override val scope: Scope by activityScope()
}

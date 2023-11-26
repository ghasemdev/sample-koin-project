package com.parsuomash.sdk.di.scope

import androidx.activity.ComponentActivity
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityScope
import org.koin.core.scope.Scope

internal abstract class ActivityScope : ComponentActivity(), AndroidScopeComponent {
  override val scope: Scope by activityScope()
}

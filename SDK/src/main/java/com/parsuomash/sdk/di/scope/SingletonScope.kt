package com.parsuomash.sdk.di.scope

import com.parsuomash.sdk.di.context.SdkKoinComponent
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.getOrCreateScope
import org.koin.core.scope.Scope

internal object SingletonScope : KoinScopeComponent, SdkKoinComponent {
  override val scope: Scope by getOrCreateScope()
}

internal inline fun singletonScope(scope: Scope.() -> Unit) {
  val singletonScope = SingletonScope.scope
  scope(singletonScope)
}

package com.parsuomash.sdk.di.scope

import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.getOrCreateScope
import org.koin.core.scope.Scope

internal object SingletonScope : KoinScopeComponent {
  override val scope: Scope by getOrCreateScope()
}

internal inline fun singletonScope(scope: Scope.() -> Unit) {
  val singletonScope = SingletonScope.scope
  scope(singletonScope)
}

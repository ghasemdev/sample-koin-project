package com.parsuomash.sdk.di.scope

import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.getOrCreateScope
import org.koin.core.scope.Scope

internal class NormalScope : KoinScopeComponent {
  override val scope: Scope by getOrCreateScope()
}

internal inline fun normalScope(scope: Scope.() -> Unit) {
  val normalScope = NormalScope().scope
  scope(normalScope)
  normalScope.close()
}

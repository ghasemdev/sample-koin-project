package com.parsuomash.sdk.di.scope

import com.parsuomash.sdk.di.context.SdkKoinComponent
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.getOrCreateScope
import org.koin.core.scope.Scope

internal class NormalScope : KoinScopeComponent, SdkKoinComponent {
  override val scope: Scope by getOrCreateScope()
}

internal inline fun normalScope(scope: Scope.() -> Unit) {
  val normalScope = NormalScope().scope
  scope(normalScope)
  normalScope.close()
}

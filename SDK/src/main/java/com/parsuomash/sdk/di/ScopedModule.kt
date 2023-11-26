package com.parsuomash.sdk.di

import android.content.Context
import com.parsuomash.sdk.SDKActivity
import com.parsuomash.sdk.di.scope.NormalScope
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Scoped
import org.koin.core.module.dsl.scopedOf
import org.koin.dsl.module

@Scope(NormalScope::class)
@Scoped
internal class Foo {
  fun log() = println("Foo")
}

@Scope(NormalScope::class)
@Scoped
internal class Bar(val foo: Foo)

@Scope(SDKActivity::class)
@Scoped
internal class Session(context: Context) {
  init {
    println("Session ${context.cacheDir.absolutePath}")
  }
}

internal val scopedModule = module {
  scope<NormalScope> {
    scopedOf(::Foo)
    scopedOf(::Bar)
  }
  scope<SDKActivity> {
    scopedOf(::Session)
  }
}

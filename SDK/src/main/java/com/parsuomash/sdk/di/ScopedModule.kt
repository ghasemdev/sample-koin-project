package com.parsuomash.sdk.di

import android.content.Context
import com.parsuomash.sdk.SDKActivity
import com.parsuomash.sdk.di.scope.SingletonScope
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Scoped
import org.koin.core.module.dsl.scopedOf
import org.koin.dsl.module

@Scope(SingletonScope::class)
@Scoped
class Foo {
  fun log() = println("Foo")
}

@Scope(SingletonScope::class)
@Scoped
class Bar(val foo: Foo)

@Scope(SDKActivity::class)
@Scoped
class Session(context: Context) {
  init {
    println("Session ${context.cacheDir.absolutePath}")
  }
}

val scopedModule = module {
  scope<SingletonScope> {
    scopedOf(::Foo)
    scopedOf(::Bar)
  }
  scope<SDKActivity> {
    scopedOf(::Session)
  }
}

package com.parsuomash.sdk.di

import android.app.Application
import android.content.Context
import com.parsuomash.sdk.SDKActivity
import com.parsuomash.sdk.di.scope.NormalScope
import org.koin.core.annotation.InjectedParam
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
internal class Session(
  @InjectedParam parameterToken: String,
//  application: Application,
  context: Context
) {
  init {
    println("parameterToken $parameterToken")
    println("Session ${context.cacheDir.absolutePath}")
//    println("PackageName ${application.packageName}")
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

package com.parsuomash.sdk.di.context

import android.content.Context
import com.parsuomash.sdk.BuildConfig
import com.parsuomash.sdk.di.SdkModule
import com.parsuomash.sdk.di.coroutinesModule
import com.parsuomash.sdk.di.sharedPrefModule
import com.parsuomash.sdk.di.tokenModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinApplication
import org.koin.core.logger.Level
import org.koin.core.module.Module
import org.koin.dsl.koinApplication
import org.koin.ksp.generated.module

internal object SdkKoinContext {
  private var app: KoinApplication? = null

  @JvmStatic
  fun start(applicationContext: Context) {
    app = buildKoinApplication(applicationContext)
  }

  @JvmStatic
  fun stop() = synchronized(this) {
    app?.close()
    app = null
  }

  @JvmStatic
  fun get(): KoinApplication =
    app ?: error("koin Application for SdkKoinContext has not been started!!")

  @JvmStatic
  fun loadKoinModules(vararg modules: Module) {
    get().koin.loadModules(modules.toList())
  }

  @JvmStatic
  fun unloadKoinModules(vararg modules: Module) {
    get().koin.unloadModules(modules.toList())
  }

  private fun buildKoinApplication(
    context: Context
  ): KoinApplication = koinApplication {
    if (BuildConfig.DEBUG) {
      androidLogger(level = Level.DEBUG)
    }
    androidContext(context)
    modules(
      coroutinesModule,
      sharedPrefModule,
      tokenModule,
      SdkModule.module
    )
  }
}

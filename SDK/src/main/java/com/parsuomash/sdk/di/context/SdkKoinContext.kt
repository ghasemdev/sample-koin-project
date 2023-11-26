package com.parsuomash.sdk.di.context

import android.content.Context
import com.parsuomash.sdk.BuildConfig
import com.parsuomash.sdk.di.SdkModule
import com.parsuomash.sdk.di.sharedPrefModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinApplication
import org.koin.core.logger.Level
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

  private fun buildKoinApplication(
    context: Context
  ): KoinApplication = koinApplication {
    if (BuildConfig.DEBUG) {
      androidLogger(level = Level.DEBUG)
    }
    androidContext(context)
    modules(SdkModule.module, sharedPrefModule)
  }
}

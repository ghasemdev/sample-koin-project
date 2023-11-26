package com.parsuomash.sdk.di.context

import com.parsuomash.sdk.di.SdkModule
import com.parsuomash.sdk.di.sharedPrefModule
import org.koin.dsl.koinApplication
import org.koin.ksp.generated.module

internal object SdkKoinContext {
  val koinApp = koinApplication {
    modules(SdkModule.module, sharedPrefModule)
  }

  val koin = koinApp.koin
}

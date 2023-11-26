package com.parsuomash.sdk.di.context

import org.koin.core.Koin
import org.koin.core.component.KoinComponent

internal interface SdkKoinComponent : KoinComponent {
  override fun getKoin(): Koin = SdkKoinContext.get().koin
}

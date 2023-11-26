package com.parsuomash.sdk.di

import com.parsuomash.sdk.provideSDK
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val sdkModule = module {
  single {
    provideSDK(androidContext()) {
      token = "1234"
    }
  }
}

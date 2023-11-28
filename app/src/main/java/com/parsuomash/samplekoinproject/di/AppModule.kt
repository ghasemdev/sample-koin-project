package com.parsuomash.samplekoinproject.di

import com.parsuomash.sdk.provideSDK
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
  single {
    provideSDK(androidContext()) {
      token = "1234"
    }
  }
}

package com.parsuomash.sdk.di

import kotlinx.coroutines.Dispatchers
import org.koin.core.module.dsl.createdAtStart
import org.koin.core.module.dsl.named
import org.koin.core.module.dsl.withOptions
import org.koin.dsl.module

internal val coroutinesModule = module {
  single {
    Dispatchers.IO
  } withOptions {
    named("IO")
    createdAtStart()
  }

  single {
    Dispatchers.Default
  } withOptions {
    named("Default")
    createdAtStart()
  }

  single {
    Dispatchers.Main
  } withOptions {
    named("Main")
    createdAtStart()
  }
}

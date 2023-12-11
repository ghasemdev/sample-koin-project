package com.parsuomash.sdk.di

import kotlinx.coroutines.Dispatchers
import org.koin.core.annotation.Module
import org.koin.core.annotation.Named
import org.koin.core.annotation.Singleton
import org.koin.core.module.dsl.createdAtStart
import org.koin.core.module.dsl.named
import org.koin.core.module.dsl.withOptions
import org.koin.dsl.module

@Module
internal object CoroutinesModule {
  operator fun invoke() = this

  @Singleton(createdAtStart = true)
  @Named("IO")
  fun provideIODispatcher() = Dispatchers.IO

  @Singleton(createdAtStart = true)
  @Named("Default")
  fun provideDefaultDispatcher() = Dispatchers.Default

  @Singleton(createdAtStart = true)
  @Named("Main")
  fun provideMainDispatcher() = Dispatchers.Main
}

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

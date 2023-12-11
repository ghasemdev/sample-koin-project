package com.parsuomash.sdk.di

import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module
import org.koin.dsl.module

@Module
internal object TokenModule {
  operator fun invoke() = this

  @Factory
  fun provideToken() = Token("dkjgfiuebvldvyl")
}

internal val tokenModule = module {
  factory {
    Token("dkjgfiuebvldvyl")
  }
}

@JvmInline
value class Token(val value: String)

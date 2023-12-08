package com.parsuomash.sdk.di

import org.koin.dsl.module

internal val tokenModule = module {
  factory {
    Token("dkjgfiuebvldvyl")
  }
}

@JvmInline
value class Token(val value: String)

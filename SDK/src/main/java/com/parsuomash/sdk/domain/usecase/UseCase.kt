package com.parsuomash.sdk.domain.usecase

import org.koin.core.annotation.Factory
import org.koin.core.annotation.Single

@Factory
internal class UseCase(val k: Koo) {
  operator fun invoke() {
    println("Usecase")
  }
}

@Single
internal class Koo

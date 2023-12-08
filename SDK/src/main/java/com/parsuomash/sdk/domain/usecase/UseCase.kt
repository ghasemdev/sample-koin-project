package com.parsuomash.sdk.domain.usecase

import com.parsuomash.sdk.di.Token
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single

@Factory
internal class UseCase(
  @Named("Default") private val dispatcher: CoroutineDispatcher,
  val k: Koo
) {
  suspend operator fun invoke() {
    withContext(dispatcher) {
      println("Usecase")
    }
  }
}

@Single
internal class Koo(token: Token) {
  init {
    println("injectToken:${token.value}")
  }
}

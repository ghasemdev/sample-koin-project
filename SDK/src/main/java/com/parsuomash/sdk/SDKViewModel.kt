package com.parsuomash.sdk

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.parsuomash.sdk.domain.usecase.UseCase
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.InjectedParam
import org.koin.core.annotation.Property

@KoinViewModel
internal class SDKViewModel(
  private val useCase: UseCase,
  @InjectedParam handle: SavedStateHandle
) : ViewModel() {
  fun foo() {
    println("ViewModel")
  }
}

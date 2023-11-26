package com.parsuomash.sdk

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.parsuomash.sdk.domain.usecase.UseCase
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
internal class SDKViewModel(
  private val savedStateHandle: SavedStateHandle,
  sdk: SDK
) : ViewModel() {
  init {
    savedStateHandle["hi"] = "hello"
    sdk.usecase()
  }

  fun foo() {
    println("ViewModel ${savedStateHandle.get<String>("hi")}")
  }
}

package com.parsuomash.sdk

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
internal class SDKViewModel(
  private val savedStateHandle: SavedStateHandle
) : ViewModel() {
  init {
    savedStateHandle["hi"] = "hello"
  }

  fun foo() {
    println("ViewModel ${savedStateHandle.get<String>("hi")}")
  }
}

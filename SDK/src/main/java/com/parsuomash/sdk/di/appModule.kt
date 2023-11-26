package com.parsuomash.sdk.di

import com.parsuomash.sdk.SDKViewModel
import com.parsuomash.sdk.domain.usecase.UseCase
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal val appModule = module {
  includes(scopedModule, sharedPrefModule)
  factoryOf(::UseCase)
  viewModelOf(::SDKViewModel)
}

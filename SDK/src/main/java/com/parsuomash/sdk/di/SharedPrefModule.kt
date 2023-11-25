package com.parsuomash.sdk.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.createdAtStart
import org.koin.core.module.dsl.named
import org.koin.core.module.dsl.withOptions
import org.koin.dsl.module

val sharedPrefModule = module {
  single {
    provideSharedPref(androidContext())
  } withOptions {
    named("SDKSharedPref")
    createdAtStart()
  }
}

private fun provideSharedPref(
  context: Context,
  name: String = context.packageName
): SharedPreferences = context.getSharedPreferences(name, MODE_PRIVATE)

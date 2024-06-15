package com.parsuomash.sdk.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.core.annotation.Module
import org.koin.core.annotation.Named
import org.koin.core.annotation.Singleton
import org.koin.core.qualifier.named
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module

@Module
internal object SharedPrefModule {
  operator fun invoke() = this

  @Singleton(createdAtStart = true)
  @Named("SDKSharedPref")
  fun provideSharedPreferences(context: Context) = provideSharedPref(context)
}

internal val sharedPrefModule = module {
  single(qualifier = qualifier("SDKSharedPref"), createdAtStart = true) {
    provideSharedPref(androidContext())
  }
}

private fun provideSharedPref(
  context: Context,
  name: String = context.packageName
): SharedPreferences = context.getSharedPreferences(name, MODE_PRIVATE)

package com.parsuomash.sdk.di

import android.content.Context
import android.content.SharedPreferences
import com.parsuomash.sdk.provideSDK
import org.koin.core.annotation.Module
import org.koin.core.annotation.Named
import org.koin.core.annotation.Singleton

@Module
internal object SdkProviderModule {
  operator fun invoke() = this

  @Singleton(createdAtStart = true)
  fun provideSDK(
    context: Context,
    @Named("SDKSharedPref") sharedPreferences: SharedPreferences
  ) = provideSDK(context) {
    token = sharedPreferences.getString("token", "null") ?: "null"
  }
}

@file:Suppress("unused")

package com.parsuomash.sdk

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.content.SharedPreferences
import android.util.Log
import androidx.annotation.Keep
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.edit
import com.parsuomash.sdk.di.context.SdkKoinComponent
import com.parsuomash.sdk.di.context.SdkKoinContext
import com.parsuomash.sdk.domain.usecase.UseCase
import kotlin.properties.Delegates
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.core.component.inject

@Keep
class SDK private constructor(
  private val token: String,
  private val context: Context
) : SdkKoinComponent {
  internal val usecase: UseCase by inject()
  internal var isLight by Delegates.notNull<Boolean>()

  private val sharedPreferences: SharedPreferences by inject()
  private val scope = CoroutineScope(Dispatchers.IO + Job())

  constructor(builder: Builder) : this(
    token = builder.token,
    context = builder.context
  ) {
    scope.launch {
      sharedPreferences.edit { putString("token", token) }
    }
  }

  init {
    SdkKoinContext.start(context)
  }

  suspend fun test() {
    usecase()
    Log.d("SDK", token)
  }

  fun startActivity(isLight: Boolean) {
    this.isLight = isLight
    scope.launch {
      sharedPreferences.edit { putBoolean("isLight", isLight) }
    }

    val intent = Intent(context, SDKActivity::class.java).apply {
      flags = FLAG_ACTIVITY_NEW_TASK
    }
    startActivity(context, intent, null)
  }

  @Keep
  class Builder(
    val context: Context,
    initializer: Builder.() -> Unit = {}
  ) {
    var token: String = ""

    init {
      initializer()
    }

    fun setToken(token: String) = apply { this.token = token }

    fun build(): SDK {
      require(token.isNotBlank())
      return SDK(this)
    }
  }
}

@Keep
fun provideSDK(context: Context, initializer: SDK.Builder.() -> Unit): SDK {
  return SDK.Builder(context, initializer).build()
}

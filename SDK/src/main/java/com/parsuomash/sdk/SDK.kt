@file:Suppress("unused")

package com.parsuomash.sdk

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import androidx.annotation.Keep
import androidx.core.content.ContextCompat.startActivity
import com.parsuomash.sdk.di.context.SdkKoinComponent
import com.parsuomash.sdk.di.context.SdkKoinContext
import com.parsuomash.sdk.domain.usecase.UseCase
import org.koin.core.component.inject

@Keep
class SDK private constructor(
  private val token: String,
  private val context: Context
) : SdkKoinComponent {
  internal val usecase: UseCase by inject()

  constructor(builder: Builder) : this(
    token = builder.token,
    context = builder.context
  )

  init {
    SdkKoinContext.start(context)
  }

  fun test() {
    usecase()
  }

  fun startActivity() {
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

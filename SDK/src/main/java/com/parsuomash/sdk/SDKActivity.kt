package com.parsuomash.sdk

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.parsuomash.sdk.di.Bar
import com.parsuomash.sdk.di.Session
import com.parsuomash.sdk.di.context.SdkKoinContext
import com.parsuomash.sdk.di.scope.ActivityScope
import com.parsuomash.sdk.di.scope.normalScope
import com.parsuomash.sdk.theme.SampleKoinProjectTheme
import com.parsuomash.sdk.ui.feature.home.HomeScreen
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.compose.KoinIsolatedContext
import org.koin.compose.module.rememberKoinModules
import org.koin.core.module.dsl.createdAtStart
import org.koin.core.module.dsl.withOptions
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

internal class SDKActivity : ActivityScope() {
  private val sharedPreferences: SharedPreferences by inject()
  private val viewModel: SDKViewModel by viewModel()
  private val session: Session by inject { parametersOf("qwer") }

  private val sdkModule = module {
    single {
      provideSDK(androidContext()) {
        token = sharedPreferences.getString("token", "null") ?: "null"
      }
    } withOptions {
      createdAtStart()
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    SdkKoinContext.start(this.application)
    SdkKoinContext.loadKoinModules(sdkModule)

    scopeTest()
    lifecycleScope.launch { viewModel.foo() }

    setContent {
      SampleKoinProjectTheme {
        // A surface container using the 'background' color from the theme
        KoinIsolatedContext(context = SdkKoinContext.get()) {
          rememberKoinModules(unloadModules = true) { listOf(sdkModule) }
          HomeScreen()
        }
      }
    }
  }

  private fun scopeTest() {
    getKoin()
    normalScope {
      get<Bar>().foo.log()
    }
    normalScope {
      get<Bar>().foo.log()
    }
    session
  }

  override fun onDestroy() {
    SdkKoinContext.unloadKoinModules(sdkModule)
    SdkKoinContext.stop()
    super.onDestroy()
  }

  override fun onNewIntent(intent: Intent?) {
    intent?.flags = FLAG_ACTIVITY_CLEAR_TASK
    super.onNewIntent(intent)
  }
}

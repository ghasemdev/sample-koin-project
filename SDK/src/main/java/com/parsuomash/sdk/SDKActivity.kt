package com.parsuomash.sdk

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.compose.setContent
import com.parsuomash.sdk.di.Bar
import com.parsuomash.sdk.di.Session
import com.parsuomash.sdk.di.context.SdkKoinContext
import com.parsuomash.sdk.di.scope.ActivityScope
import com.parsuomash.sdk.di.scope.normalScope
import com.parsuomash.sdk.theme.SampleKoinProjectTheme
import com.parsuomash.sdk.ui.feature.home.HomeScreen
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.compose.KoinIsolatedContext
import org.koin.dsl.module

internal class SDKActivity : ActivityScope() {
  private val sharedPreferences: SharedPreferences by inject()
  private val viewModel: SDKViewModel by viewModel()
  private val session: Session by inject()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    SdkKoinContext.start(this)
    SdkKoinContext.loadKoinModules(
      module {
        single {
          provideSDK(androidContext()) {
            token = sharedPreferences.getString("token", "null") ?: "null"
          }
        }
      }
    )

    scopeTest()
    viewModel.foo()

    setContent {
      SampleKoinProjectTheme {
        // A surface container using the 'background' color from the theme
        KoinIsolatedContext(context = SdkKoinContext.get()) {
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
    SdkKoinContext.stop()
    super.onDestroy()
  }

  override fun onNewIntent(intent: Intent?) {
    intent?.flags = FLAG_ACTIVITY_CLEAR_TASK
    super.onNewIntent(intent)
  }
}

package com.parsuomash.sdk

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.core.content.edit
import com.parsuomash.sdk.di.Bar
import com.parsuomash.sdk.di.SdkModule
import com.parsuomash.sdk.di.Session
import com.parsuomash.sdk.di.scope.ActivityScope
import com.parsuomash.sdk.di.scope.singletonScope
import com.parsuomash.sdk.di.sharedPrefModule
import com.parsuomash.sdk.theme.SampleKoinProjectTheme
import com.parsuomash.sdk.ui.feature.home.HomeScreen
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.logger.Level
import org.koin.ksp.generated.module

internal class SDKActivity : ActivityScope() {
  private val sharedPref: SharedPreferences by inject()
  private val viewModel: SDKViewModel by viewModel()
  private val session: Session by inject()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    installKoin()
    scopeTest()
    sharedPref.edit { putString("key", "one") }
    viewModel.foo()

    setContent {
      SampleKoinProjectTheme {
        // A surface container using the 'background' color from the theme
        KoinAndroidContext {
          HomeScreen()
        }
      }
    }
  }

  private fun scopeTest() {
    getKoin()
    singletonScope {
      get<Bar>().foo.log()
    }
    singletonScope {
      get<Bar>().foo.log()
    }
    session
  }

  private fun installKoin() {
    startKoin {
      if (BuildConfig.DEBUG) {
        androidLogger(level = Level.DEBUG)
      }
      androidContext(this@SDKActivity)
      modules(SdkModule.module, sharedPrefModule)
    }
  }

  override fun onDestroy() {
    stopKoin()
    super.onDestroy()
  }
}

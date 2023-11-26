package com.parsuomash.sdk

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.core.content.edit
import com.parsuomash.sdk.di.Bar
import com.parsuomash.sdk.di.Session
import com.parsuomash.sdk.di.context.SdkKoinContext
import com.parsuomash.sdk.di.scope.ActivityScope
import com.parsuomash.sdk.di.scope.singletonScope
import com.parsuomash.sdk.theme.SampleKoinProjectTheme
import com.parsuomash.sdk.ui.feature.home.HomeScreen
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.compose.KoinIsolatedContext

internal class SDKActivity : ActivityScope() {
  private val sharedPref: SharedPreferences by inject()
  private val viewModel: SDKViewModel by viewModel()
  private val session: Session by inject()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    SdkKoinContext.start(this)
    scopeTest()
    sharedPref.edit { putString("key", "one") }
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
    singletonScope {
      get<Bar>().foo.log()
    }
    singletonScope {
      get<Bar>().foo.log()
    }
    session
  }

  override fun onDestroy() {
    SdkKoinContext.stop()
    super.onDestroy()
  }
}

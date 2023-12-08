package com.parsuomash.samplekoinproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.parsuomash.samplekoinproject.di.appModule
import com.parsuomash.samplekoinproject.ui.theme.SampleKoinProjectTheme
import com.parsuomash.sdk.SDK
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class MainActivity : ComponentActivity() {
  private val sdk: SDK by inject()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    installKoin()
    lifecycleScope.launch { sdk.test() }

    setContent {
      SampleKoinProjectTheme {
        // A surface container using the 'background' color from the theme
        Surface(
          modifier = Modifier.fillMaxSize(),
          color = MaterialTheme.colors.background
        ) {
          Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
          ) {
            Button(
              onClick = {
                sdk.startActivity(true)
              }
            ) {
              Text(text = "Open Sdk")
            }
          }
        }
      }
    }
  }

  private fun installKoin() {
    startKoin {
      androidContext(this@MainActivity)
      modules(appModule)
    }
  }

  override fun onDestroy() {
    stopKoin()
    super.onDestroy()
  }
}

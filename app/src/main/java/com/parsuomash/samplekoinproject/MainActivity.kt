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
import com.parsuomash.samplekoinproject.ui.theme.SampleKoinProjectTheme
import com.parsuomash.sdk.provideSDK

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

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
                val sdk = provideSDK(applicationContext) { token = "1234" }
                sdk.test()
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
}

package com.parsuomash.sdk.ui.feature.home

import android.content.SharedPreferences
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.edit
import com.parsuomash.sdk.SDKViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.qualifier.named

@Composable
internal fun HomeScreen(
  viewModel: SDKViewModel = koinViewModel(),
  sharedPref: SharedPreferences = koinInject(named("SDKSharedPref"))
) {
  LaunchedEffect(Unit) {
    viewModel.foo()
    withContext(Dispatchers.IO) {
      sharedPref.edit { putString("HomeScreen", "HomeScreen") }
    }
  }

  Surface(
    modifier = Modifier.fillMaxSize(),
    color = MaterialTheme.colors.background
  ) {
    Box(
      modifier = Modifier.fillMaxSize(),
      contentAlignment = Alignment.Center
    ) {
      Text(text = "SDK", style = MaterialTheme.typography.h1)
    }
  }
}

@Preview
@Composable
private fun HomeScreenPreview() {
  MaterialTheme {
    HomeScreen()
  }
}

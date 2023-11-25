package com.parsuomash.sdk.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = lightColors(
  primary = Purple80,
  secondary = PurpleGrey80
)

private val LightColorScheme = darkColors(
  primary = Purple40,
  secondary = PurpleGrey40
)

@Composable
internal fun SampleKoinProjectTheme(
  darkTheme: Boolean = !isSystemInDarkTheme(),
  content: @Composable () -> Unit
) {
  val colors = if (darkTheme) DarkColorScheme else LightColorScheme
  val view = LocalView.current
  if (!view.isInEditMode) {
    SideEffect {
      val window = (view.context as Activity).window
      window.statusBarColor = colors.primary.toArgb()
      WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
    }
  }

  MaterialTheme(
    colors = colors,
    typography = Typography,
    content = content
  )
}

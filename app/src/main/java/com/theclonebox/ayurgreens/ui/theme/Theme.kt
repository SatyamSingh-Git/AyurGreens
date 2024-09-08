package com.theclonebox.ayurgreens.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.theclonebox.ayurgreens.R

private val LightColorScheme = lightColorScheme(
    primary = Heading,
    secondary = SubHeading,
    tertiary = White,
)

val CustomFontFamily = FontFamily(
    Font(R.font.montserrat_bold, FontWeight.W900)
)

@Composable
fun AyurGreensTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (isSystemInDarkTheme()) {
        LightColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}
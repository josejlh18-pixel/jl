package com.jornalera.v3.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Ink = Color(0xFF182022)
val Moss = Color(0xFF1F5A47)
val Mint = Color(0xFFDCF4E6)
val Sand = Color(0xFFF8F5EE)
val Clay = Color(0xFFD96D4D)
val Sky = Color(0xFFDCECF2)

private val JornaleraColors = lightColorScheme(
    primary = Moss,
    onPrimary = Color.White,
    primaryContainer = Mint,
    onPrimaryContainer = Ink,
    secondary = Clay,
    background = Sand,
    onBackground = Ink,
    surface = Color.White,
    onSurface = Ink,
    surfaceVariant = Color(0xFFF0EEE7),
    outline = Color(0xFF77817D)
)

@Composable
fun JornaleraTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = JornaleraColors, typography = JornaleraTypography, content = content)
}

package com.jornalera.v3.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val JornaleraTypography = Typography(
    displaySmall = androidx.compose.ui.text.TextStyle(fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold, fontSize = 34.sp, lineHeight = 39.sp),
    headlineSmall = androidx.compose.ui.text.TextStyle(fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold, fontSize = 25.sp, lineHeight = 30.sp),
    titleLarge = androidx.compose.ui.text.TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Bold, fontSize = 20.sp),
    titleMedium = androidx.compose.ui.text.TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.SemiBold, fontSize = 16.sp),
    bodyMedium = androidx.compose.ui.text.TextStyle(fontFamily = FontFamily.Default, fontSize = 14.sp, lineHeight = 20.sp),
    labelLarge = androidx.compose.ui.text.TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Bold, fontSize = 14.sp)
)

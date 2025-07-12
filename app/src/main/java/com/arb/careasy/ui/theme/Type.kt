package com.arb.careasy.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
object AppTextStyle {

    // Heading styles
    val headingLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 40.sp,
        color = AppColors.White
    )

    val headingMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 25.sp,
        color = AppColors.White
    )

    val headingSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        color = AppColors.White
    )

    // Body styles
    val bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        color = AppColors.White
    )

    val bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = AppColors.White
    )

    val bodyRegular = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = AppColors.White
    )

    val bodySmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        color = AppColors.White
    )

    // Button text
    val buttonText = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        color = AppColors.White
    )
}

// Material 3 Typography for the theme
val Typography = Typography(
    bodyLarge = AppTextStyle.bodyLarge,
    bodyMedium = AppTextStyle.bodyMedium,
    bodySmall = AppTextStyle.bodySmall,
    headlineLarge = AppTextStyle.headingLarge,
    headlineMedium = AppTextStyle.headingMedium,
    headlineSmall = AppTextStyle.headingSmall
)
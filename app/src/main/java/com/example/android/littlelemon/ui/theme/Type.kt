package com.example.android.littlelemon.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.android.littlelemon.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )

    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

// Little Lemon TextStyle
object LittleLemonTextStyle  {
    private val markaziFontFamily = FontFamily(
        Font(R.font.markazi_text_regular, FontWeight.Normal)
    )

    private val karlaFontFamily = FontFamily(
        Font(R.font.karla_regular, FontWeight.Normal)
    )

    val displayTitle = TextStyle(
        fontFamily = markaziFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 56.sp
    )

    val subTitle = TextStyle(
        fontFamily = markaziFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 42.sp
    )

    val leadText = TextStyle(
        fontFamily = karlaFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp
    )

    val sectionTitle = TextStyle(
        fontFamily = karlaFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 18.sp
    )

    val sectionCategories = TextStyle(
        fontFamily = karlaFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 16.sp
    )

    val cardTitle = TextStyle(
        fontFamily = karlaFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    )

    val paragraphText = TextStyle(
        fontFamily = karlaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 1.5.sp
    )

    val highLightText = TextStyle(
        fontFamily = karlaFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    )

}

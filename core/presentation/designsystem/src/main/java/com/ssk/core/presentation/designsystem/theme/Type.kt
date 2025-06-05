@file:OptIn(ExperimentalTextApi::class)

package com.ssk.core.presentation.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.unit.sp
import com.ssk.core.presentation.designsystem.R

// Set of Material typography styles to start with
val InterRegular = FontFamily(
    Font(
        resId = R.font.inter_variablefont_weight,
        variationSettings = FontVariation.Settings(
            FontVariation.weight(400)
        )
    )
)

val InterMedium = FontFamily(
    Font(
        resId = R.font.inter_variablefont_weight,
        variationSettings = FontVariation.Settings(
            FontVariation.weight(500)
        )
    )
)

val InterSemiBold = FontFamily(
    Font(
        resId = R.font.inter_variablefont_weight,
        variationSettings = FontVariation.Settings(
            FontVariation.weight(600)
        )
    )
)

val SpaceRegular = FontFamily(
    Font(
        resId = R.font.spacegrotesk_variablegfont_weight,
        variationSettings = FontVariation.Settings(
            FontVariation.weight(400)
        )
    )
)

val SpaceMedium = FontFamily(
    Font(
        resId = R.font.spacegrotesk_variablegfont_weight,
        variationSettings = FontVariation.Settings(
            FontVariation.weight(500)
        )
    )
)

val SpaceSemiBold = FontFamily(
    Font(
        resId = R.font.spacegrotesk_variablegfont_weight,
        variationSettings = FontVariation.Settings(
            FontVariation.weight(600)
        )
    )
)

val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = SpaceSemiBold,
        fontSize = 36.sp,
        lineHeight = 40.sp
    ),
    titleMedium = TextStyle(
        fontFamily = SpaceSemiBold,
        fontSize = 32.sp,
        lineHeight = 36.sp,
    ),

    titleSmall = TextStyle(
        fontFamily = SpaceMedium,
        fontSize = 17.sp,
        lineHeight = 24.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = InterRegular,
        fontSize = 17.sp,
        lineHeight = 24.sp
    ),

    bodyMedium = TextStyle(
        fontFamily = InterMedium,
        fontSize = 15.sp,
        lineHeight = 20.sp
    ),
    bodySmall = TextStyle(
        fontFamily = InterRegular,
        fontSize = 15.sp,
        lineHeight = 20.sp,
    )
)

package com.kedijik.littlelemon.ui.theme



import androidx.compose.material.Typography
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.sp
import com.kedijik.littlelemon.R

// Set of Material typography styles to start with
@OptIn(ExperimentalTextApi::class)
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily(Font(R.font.karla_regular)),
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = DarkGrey

    ),
    h1 = TextStyle(
        fontFamily = FontFamily(Font(R.font.markazitext_regular)),
        lineHeightStyle = LineHeightStyle(alignment = LineHeightStyle.Alignment.Bottom, trim = LineHeightStyle.Trim.FirstLineTop),
        fontSize = 64.sp,
        color = Yellow

    ),
    h3 = TextStyle(
        fontFamily = FontFamily(Font(R.font.markazitext_regular)),
        fontSize = 40.sp,
        color = White


    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)
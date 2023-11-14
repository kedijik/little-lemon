package com.kedijik.littlelemon.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.kedijik.littlelemon.R

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily(Font(R.font.karla_regular)),
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = Color.DarkGray

    ),
    h1 = TextStyle(
        fontFamily = FontFamily(Font(R.font.markazitext_regular)),
        fontWeight = FontWeight.Bold,

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
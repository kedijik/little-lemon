package com.kedijik.littlelemon.ui.theme

import android.annotation.SuppressLint
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

/*private val DarkColorPalette = darkColors(

            primary = DarkGreen,
    primaryVariant = DarkestGreen,
    secondary = Yellow,
    surface = DarkestGreen,
    background = White,
)*/

@SuppressLint("ConflictingOnColor")
private val LightColorPalette = lightColors(
    primary = DarkGreen,
    primaryVariant = Beige,
    secondary = DarkestGreen,
    surface = DarkestGreen,
    background = White,
    onPrimary = DarkGrey,
    onSecondary = LightGrey,
    secondaryVariant = Yellow,
    onSurface = Salmon



    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun LittleLemonTheme( content: @Composable () -> Unit) {
    val colors = LightColorPalette /*if (darkTheme) {
        LightColorPalette
    } else {
        LightColorPalette
    }*/

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
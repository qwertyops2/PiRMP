package com.example.myapplication

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier

private val lightColor = lightColorScheme(
    primary = Color(0xFFFFC9A9),
    onPrimary = Color.Black,
    background = Color.White,
    onBackground = Color.Black
)

private val darkColor = darkColorScheme(
    primary = Color(0xFF7953AD),
    onPrimary = Color.White,
    background = Color(0xFF121212),
    onBackground = Color.White
)

@Composable
fun AppTheme(
    darkTheme: Boolean,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) darkColor else lightColor
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground
        ) {
            content()
        }
    }
}
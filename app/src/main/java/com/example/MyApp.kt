package com.example

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.ui.theme.ComposeBasicsTheme

@Composable
fun MyApp(content: @Composable () -> Unit) {
    ComposeBasicsTheme {
        Surface(color = Color.Yellow) {
            content()
        }
    }
}
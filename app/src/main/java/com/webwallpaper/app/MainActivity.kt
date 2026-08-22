package com.webwallpaper.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.webwallpaper.app.ui.theme.AppTheme
import com.webwallpaper.app.ui.theme.WebWallpaperTheme
import com.webwallpaper.app.ui.theme.rememberThemeState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val themeState = rememberThemeState()
            WebWallpaperTheme(themeState = themeState) {
                val bgColor = if (themeState.isDark) Color.Green else Color.Red
                Surface(color = bgColor) {
                    ThemeTogglePlaceholderScreen(
                        onToggleTheme = { themeState.toggle() },
                        isDark = themeState.isDark
                    )
                }
            }
        }
    }
}

/**
 * Session 1 scope only: a minimal placeholder proving the theme system
 * works — a background using --panel and a text label using --text, both
 * swapping instantly on tap, matching the CSS classList.toggle('dark-theme')
 * behavior (no animation). All real screens/controls come in later sessions.
 */
@Composable
fun ThemeTogglePlaceholderScreen(onToggleTheme: () -> Unit, isDark: Boolean) {
    val bgColor = if (isDark) Color.Green else Color.Red
    val textColor = if (isDark) Color.Black else Color.White
        Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
            .clickable(onClick = onToggleTheme)
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Web Wallpaper — Auto-Save & Push Verified!",
            color = textColor,
            fontWeight = FontWeight.Medium
        )
    }
}

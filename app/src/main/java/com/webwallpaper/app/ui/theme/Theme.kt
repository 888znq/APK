package com.webwallpaper.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

/**
 * Mirrors the original web behavior:
 *   document.documentElement.classList.toggle('dark-theme')
 * i.e. an instant, un-animated swap between two fixed palettes. There is no
 * cross-fade or color animation in the source CSS, so none is added here.
 *
 * The web demo does not read the OS theme (it defaults to light and only
 * changes via the on-screen sun/moon button), so the initial state below
 * defaults to light rather than following isSystemInDarkTheme(), to stay
 * faithful to the original's actual runtime behavior. isSystemInDarkTheme()
 * is imported but intentionally unused for the initial value — kept as a
 * documented decision point in case a later session wants app-launch to
 * follow system theme instead (would be a deviation from the demo and
 * should be flagged/approved in PROGRESS.md Section 4 if changed).
 */
class ThemeState {
    var isDark by mutableStateOf(false)
        private set

    fun toggle() {
        isDark = !isDark
    }
}

@Composable
fun rememberThemeState(): ThemeState = remember { ThemeState() }

@Composable
fun WebWallpaperTheme(
    themeState: ThemeState,
    content: @Composable () -> Unit
) {
    val appColors = if (themeState.isDark) DarkAppColors else LightAppColors

    CompositionLocalProvider(
        LocalAppColors provides appColors,
        LocalAppTypography provides DefaultAppTypography,
        LocalAppDimens provides DefaultAppDimens
    ) {
        MaterialTheme(
            content = content
        )
    }
}

/** Convenience accessor: `AppTheme.colors.text`, `AppTheme.typography...`, `AppTheme.dimens...` */
object AppTheme {
    val colors: AppColors
        @Composable
        get() = LocalAppColors.current

    val typography: AppTypography
        @Composable
        get() = LocalAppTypography.current

    val dimens: AppDimens
        @Composable
        get() = LocalAppDimens.current
}

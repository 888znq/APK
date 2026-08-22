package com.webwallpaper.app.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * Design tokens extracted VERBATIM from the source CSS (`index (17).html`).
 * Every value here must trace back exactly to a `:root` / `:root.dark-theme`
 * CSS custom property. Do NOT approximate, round, or invent new colors.
 *
 * Source CSS:
 *   :root{
 *     --ink: #f4f4f5;
 *     --panel: #ffffff;
 *     --field: #e4e4e7;
 *     --hair: #d4d4d8;
 *     --text: #27272a;
 *     --dim: #71717a;
 *     --accent:#111111;
 *     --accent-dim:#333333;
 *     --ok:#111111;
 *   }
 *   :root.dark-theme{
 *     --ink: #000000;
 *     --panel: #1c1c1e;
 *     --field: #2c2c2e;
 *     --hair: #3a3a3c;
 *     --text: #f4f4f5;
 *     --dim: #9a9aa0;
 *     --accent:#ffffff;
 *     --accent-dim:#cccccc;
 *     --ok:#ffffff;
 *   }
 */

// ---- Light theme (from CSS :root) ----
val LightInk = Color(0xFFF4F4F5)        // --ink:        #f4f4f5
val LightPanel = Color(0xFFFFFFFF)      // --panel:      #ffffff
val LightField = Color(0xFFE4E4E7)      // --field:      #e4e4e7
val LightHair = Color(0xFFD4D4D8)       // --hair:       #d4d4d8
val LightText = Color(0xFF27272A)       // --text:       #27272a
val LightDim = Color(0xFF71717A)        // --dim:        #71717a
val LightAccent = Color(0xFF111111)     // --accent:     #111111
val LightAccentDim = Color(0xFF333333)  // --accent-dim: #333333
val LightOk = Color(0xFF111111)         // --ok:         #111111

// ---- Dark theme (from CSS :root.dark-theme) ----
val DarkInk = Color(0xFF000000)         // --ink:        #000000
val DarkPanel = Color(0xFF1C1C1E)       // --panel:      #1c1c1e
val DarkField = Color(0xFF2C2C2E)       // --field:      #2c2c2e
val DarkHair = Color(0xFF3A3A3C)        // --hair:       #3a3a3c
val DarkText = Color(0xFFF4F4F5)        // --text:       #f4f4f5
val DarkDim = Color(0xFF9A9AA0)         // --dim:        #9a9aa0
val DarkAccent = Color(0xFFFFFFFF)      // --accent:     #ffffff
val DarkAccentDim = Color(0xFFCCCCCC)   // --accent-dim: #cccccc
val DarkOk = Color(0xFFFFFFFF)          // --ok:         #ffffff

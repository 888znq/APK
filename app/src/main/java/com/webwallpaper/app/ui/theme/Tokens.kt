package com.webwallpaper.app.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Semantic color set — one instance per theme (light / dark), mirroring the
 * CSS variable names 1:1 (--ink, --panel, --field, --hair, --text, --dim,
 * --accent, --accent-dim, --ok) so later sessions can map `AppColors.text`
 * etc. directly back to the source design without re-deriving anything.
 */
@Immutable
data class AppColors(
    val ink: Color,
    val panel: Color,
    val field: Color,
    val hair: Color,
    val text: Color,
    val dim: Color,
    val accent: Color,
    val accentDim: Color,
    val ok: Color
)

val LightAppColors = AppColors(
    ink = LightInk,
    panel = LightPanel,
    field = LightField,
    hair = LightHair,
    text = LightText,
    dim = LightDim,
    accent = LightAccent,
    accentDim = LightAccentDim,
    ok = LightOk
)

val DarkAppColors = AppColors(
    ink = DarkInk,
    panel = DarkPanel,
    field = DarkField,
    hair = DarkHair,
    text = DarkText,
    dim = DarkDim,
    accent = DarkAccent,
    accentDim = DarkAccentDim,
    ok = DarkOk
)

/**
 * Typography tokens extracted from the CSS. Only the values present in the
 * CSS source as of Session 1 are included below; more will be appended in
 * later sessions as more of the UI is ported (per PROGRESS.md Section 1).
 *
 * CSS px -> Compose sp at 1:1 ratio (dp conversion rule from 01-SETUP.md
 * applies the same way to text sizing here).
 */
@Immutable
data class AppTypography(
    val fieldLabelSize: TextUnit,       // .field-wrap label: font-size:10px
    val fieldLabelWeight: Int,          //                    font-weight:500
    val toggleLabelSize: TextUnit,      // .toggle-label: font-size:9px
    val toggleLabelWeight: Int,         //                font-weight:700
    val toggleLabelLetterSpacing: TextUnit, //             letter-spacing:0.05em -> approximated in sp per-element in later session
    val btnSize: TextUnit,              // .btn: font-size:10px
    val btnWeight: Int,                 //       font-weight:600
    val segBtnSize: TextUnit,           // .seg-btn: font-size:11px
    val segBtnWeight: Int,              //           font-weight:700
    val statusRowSize: TextUnit,        // .status-row: font-size:11px
    val statusRowWeight: Int,           //              font-weight:500
    val clockTimeSize: TextUnit,        // .clock .time: font-size:48px
    val clockTimeWeight: Int,           //               font-weight:200
    val clockDateSize: TextUnit,        // .clock .date: font-size:12px
    val clockDateWeight: Int            //                font-weight:500
)

val DefaultAppTypography = AppTypography(
    fieldLabelSize = 10.sp,
    fieldLabelWeight = 500,
    toggleLabelSize = 9.sp,
    toggleLabelWeight = 700,
    toggleLabelLetterSpacing = 0.45.sp, // 0.05em @ 9sp base, revisit precision in later session if needed
    btnSize = 10.sp,
    btnWeight = 600,
    segBtnSize = 11.sp,
    segBtnWeight = 700,
    statusRowSize = 11.sp,
    statusRowWeight = 500,
    clockTimeSize = 48.sp,
    clockTimeWeight = 200,
    clockDateSize = 12.sp,
    clockDateWeight = 500
)

/**
 * Spacing / radius / sizing tokens extracted from the CSS. dp conversion
 * rule: CSS px -> Android dp at 1:1 ratio (per 01-SETUP.md), unless flagged
 * as an approved deviation in PROGRESS.md Section 4.
 */
@Immutable
data class AppDimens(
    val fieldHeight: Dp,          // input[type=text], select: height:24px
    val btnHeight: Dp,            // .btn: height:24px
    val toggleTrackWidth: Dp,     // .toggle: width:34px
    val toggleTrackHeight: Dp,    // .toggle: height:18px
    val toggleTrackRadius: Dp,    // .toggle: border-radius:10px
    val toggleThumbSize: Dp,      // .toggle::after: width:14px height:14px
    val segmentControlPadding: Dp,// .segment-control: padding:4px
    val segmentControlGap: Dp,    // .segment-control: gap:4px
    val historyGridGap: Dp        // #historyGrid: gap:8px
)

val DefaultAppDimens = AppDimens(
    fieldHeight = 24.dp,
    btnHeight = 24.dp,
    toggleTrackWidth = 34.dp,
    toggleTrackHeight = 18.dp,
    toggleTrackRadius = 10.dp,
    toggleThumbSize = 14.dp,
    segmentControlPadding = 4.dp,
    segmentControlGap = 4.dp,
    historyGridGap = 8.dp
)

val LocalAppColors = staticCompositionLocalOf { LightAppColors }
val LocalAppTypography = staticCompositionLocalOf { DefaultAppTypography }
val LocalAppDimens = staticCompositionLocalOf { DefaultAppDimens }

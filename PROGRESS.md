# PROGRESS.md — Web Wallpaper Android Port

Last updated: Session 1 (post-CI-fix)
Last session run: 01-SETUP
Repo build status as of last push: FAILING on the first real GitHub Actions run — one Kotlin compile error found and fixed locally (see Section 7); **fix has not yet been re-verified by a second CI run**, so treat as unconfirmed until the next push comes back green

---

## 1. Locked Design Tokens
(Filled in during Session 1. Every later session MUST reuse these exact
values — never redefine or approximate them.)

### Colors — Light theme (from `:root`)
| Token | Hex/RGBA | Used for | Kotlin constant |
|---|---|---|---|
| --ink | #f4f4f5 | app/system background layer, launcher icon background | `LightInk` |
| --panel | #ffffff | screen/surface background | `LightPanel` |
| --field | #e4e4e7 | input fields, buttons, chips | `LightField` |
| --hair | #d4d4d8 | hairline borders/dividers | `LightHair` |
| --text | #27272a | primary text | `LightText` |
| --dim | #71717a | secondary/dim text | `LightDim` |
| --accent | #111111 | accent/primary action color, launcher icon mark | `LightAccent` |
| --accent-dim | #333333 | dimmed accent variant | `LightAccentDim` |
| --ok | #111111 | success/confirmation state | `LightOk` |

### Colors — Dark theme (from `.dark-theme`)
| Token | Hex/RGBA | Used for | Kotlin constant |
|---|---|---|---|
| --ink | #000000 | app/system background layer | `DarkInk` |
| --panel | #1c1c1e | screen/surface background | `DarkPanel` |
| --field | #2c2c2e | input fields, buttons, chips | `DarkField` |
| --hair | #3a3a3c | hairline borders/dividers | `DarkHair` |
| --text | #f4f4f5 | primary text | `DarkText` |
| --dim | #9a9aa0 | secondary/dim text | `DarkDim` |
| --accent | #ffffff | accent/primary action color | `DarkAccent` |
| --accent-dim | #cccccc | dimmed accent variant | `DarkAccentDim` |
| --ok | #ffffff | success/confirmation state | `DarkOk` |

All nine tokens per theme are implemented in
`app/src/main/java/com/webwallpaper/app/ui/theme/Color.kt` (raw hex constants)
and grouped semantically in
`app/src/main/java/com/webwallpaper/app/ui/theme/Tokens.kt` (`AppColors` data
class, `LightAppColors` / `DarkAppColors` instances). Access at runtime via
`AppTheme.colors.<name>` (e.g. `AppTheme.colors.text`).

**Theme switch behavior**: implemented as an instant, un-animated swap
(`ThemeState.isDark` boolean flips `AppColors` instance via
`CompositionLocalProvider`), matching the CSS's instant
`classList.toggle('dark-theme')` with no transition. See
`ui/theme/Theme.kt`.

**Flagged decision (not yet a "deviation" needing Section 4, but worth
tracking)**: the placeholder screen defaults to **light theme on launch**,
matching the web demo's actual default behavior (`:root` with no
`.dark-theme` class = light, until the user taps the theme button). This is
documented in a code comment in `Theme.kt`. If a later session wants
launch-time theme to instead follow the OS dark/light setting, that would be
a deliberate deviation from the source and should be logged in Section 4 at
that time.

### Typography
Only values traceable to CSS selectors that exist in the current single-page
demo are included. Extracted so far:

| Element | Size | Weight | Letter-spacing | Transform |
|---|---|---|---|---|
| field-wrap label | 10px → 10sp | 500 | — | — |
| toggle-label | 9px → 9sp | 700 | 0.05em | uppercase |
| btn | 10px → 10sp | 600 | 0.02em | uppercase |
| seg-btn | 11px → 11sp | 700 | — | uppercase |
| status-row | 11px → 11sp | 500 | — | — |
| clock time | 48px → 48sp | 200 | -1px | — |
| clock date | 12px → 12sp | 500 | — | — |

Implemented in `ui/theme/Tokens.kt` as `AppTypography` /
`DefaultAppTypography`. **Note**: `toggle-label`'s `letter-spacing: 0.05em`
was converted to an approximate absolute sp value (0.45sp, i.e. 0.05 × 9sp)
because Compose's `TextStyle.letterSpacing` doesn't take an em-relative unit
tied dynamically to that Text's own font size the way CSS `em` does. This is
flagged here rather than silently applied — if per-composable relative em
calculation is required for fidelity, revisit in the session that actually
builds the toggle component and add a proper approved-deviation row to
Section 4 then (not enough context yet in Session 1 to judge if the 1:1
approximation holds visually).

Uppercase-transform, letter-spacing on other elements, `text-transform`, and
any typography for elements not yet listed above (there are many more in the
full CSS — labels, inputs, history-type tags, etc.) are **not yet
extracted** and will be added incrementally as each owning component is
built in its dedicated session, per the template's instruction to "extend as
more are extracted."

### Spacing / Radius / Sizing
| Element | Value | Kotlin constant |
|---|---|---|
| field height (input/select) | 24px → 24dp | `fieldHeight` |
| btn height (default) | 24px → 24dp | `btnHeight` |
| toggle track | 34x18px → 34x18dp, radius 10px → 10dp | `toggleTrackWidth/Height/Radius` |
| toggle thumb | 14x14px → 14x14dp | `toggleThumbSize` |
| segment control padding | 4px → 4dp | `segmentControlPadding` |
| segment control gap | 4px → 4dp | `segmentControlGap` |
| history grid gap | 8px → 8dp | `historyGridGap` |

Implemented in `ui/theme/Tokens.kt` as `AppDimens` / `DefaultAppDimens`.
Many more spacing/radius values exist in the full CSS (miniFrame container,
dock, popups, sliders, etc.) — not yet extracted; will be added when each
owning component/screen is built in a later session, per template
instruction.

### dp Conversion Rule
CSS px → Android dp at 1:1 ratio. Applied this way for every value above.
No exceptions were needed in Session 1 (only the em-based letter-spacing
note above required a non-literal conversion, and that's flagged, not
silently resolved).

---

## 2. Components Built So Far
| Component | File path in repo | Session built | Status |
|---|---|---|---|
| Theme system (colors/typography/dimens tokens + instant light/dark toggle) | `app/src/main/java/com/webwallpaper/app/ui/theme/Color.kt`, `Tokens.kt`, `Theme.kt` | 01-SETUP | Done for Session 1 scope (tokens complete for values specified in the session brief; more tokens to be appended per-component in later sessions) |

---

## 3. Screens Built So Far
| Screen | File path in repo | Session built | Status | Visual diff confirmed? |
|---|---|---|---|---|
| Placeholder theme-toggle screen (proof-of-concept only, not a real app screen) | `app/src/main/java/com/webwallpaper/app/MainActivity.kt` | 01-SETUP | Done — minimal by design, per session scope | No — not a real screen, nothing to diff against; will be deleted/replaced when the real settings screen is built |

---

## 4. Approved Rule-6 Deviations (things that couldn't be 1:1)
| HTML/CSS element | Why it couldn't be exact | Android substitute used | Approved? |
|---|---|---|---|
| (none requiring approval yet) | `toggle-label` CSS `letter-spacing: 0.05em` converted to a static 0.45sp instead of a live em-relative unit (Compose has no built-in em-relative-to-this-text-node letterSpacing) | Static sp approximation, computed as 0.05 × 9sp | **Not yet approved — flagged for review** when the toggle component is actually built |

---

## 5. Shared State / Data Layer Decisions
- Persistence mechanism: **Not yet decided.** Session 1 has zero persisted
  state (the placeholder screen's theme toggle lives only in in-memory
  Compose state via `ThemeState`/`mutableStateOf`, reset on process death).
  A later session (likely the one building saved-URL / history / applied
  wallpaper target) needs to choose between Jetpack DataStore
  (Preferences or Proto) vs. SharedPreferences vs. a small Room DB —
  the original web demo uses only in-memory JS state (`historyList`,
  `savedUrl`, etc., all lost on page reload), so there's no existing
  "source of truth" persistence shape to mirror 1:1. This needs an explicit
  decision in whichever session tackles history/persistence, not an
  assumption made here.
- Keys defined so far: none yet.

---

## 6. GitHub Actions / Build Config Notes
- Workflow file path: `.github/workflows/build.yml`
- Triggers: push to `main`, PRs to `main`, and manual `workflow_dispatch`.
- Steps: checkout → JDK 17 (Temurin) → validate Gradle wrapper → `chmod +x
  gradlew` → `./gradlew assembleDebug --stacktrace` → upload
  `app-debug.apk` as a build artifact.
- Versions pinned:
  - Gradle: **8.7** (`gradle/wrapper/gradle-wrapper.properties`)
  - Android Gradle Plugin (AGP): **8.5.2** (root `build.gradle.kts`)
  - Kotlin: **1.9.24** (root `build.gradle.kts`)
  - Compose BOM: **2024.06.00** (`app/build.gradle.kts`)
  - Compose Compiler extension: **1.5.14** (compatible with Kotlin 1.9.24
    per the standard AGP/Compose compatibility map)
  - compileSdk / targetSdk: **34**, minSdk: **26** (chosen so `WebView`-style
    rendering behavior in later sessions has a modern, consistent baseline;
    flag if a lower minSdk is actually required for target devices — not
    specified in the session brief, so 26 was chosen as a reasonable
    Compose-era floor, not extracted from any source-of-truth requirement)
  - JDK: **17** (Temurin, via `actions/setup-java@v4`)
- Any build warnings intentionally left unresolved: none identified yet —
  **see honesty note below**, this hasn't been verified by an actual
  Gradle invocation.

### ⚠️ Build verification status — read this
The first real GitHub Actions run **failed**, confirming the risk flagged in
the previous version of this section (local sandbox couldn't reach
`services.gradle.org`, so no build had actually been executed before that
first push).

**Actual failure from CI:**
```
Theme.kt:26:19 Type 'MutableState<TypeVariable(T)>' has no method
'getValue(ThemeState, KProperty<*>)' and thus it cannot serve as a delegate
```

**Root cause**: `ThemeState.isDark` used the `var x by mutableStateOf(...)`
property-delegate syntax, but `Theme.kt` only imported `mutableStateOf` and
`remember` — it was missing the two operator-function imports Kotlin needs
to resolve the `by` delegate against `MutableState`:
`androidx.compose.runtime.getValue` and `androidx.compose.runtime.setValue`.
Without those, the compiler can't find a `getValue`/`setValue` operator on
`MutableState` and delegation fails. This is a very common
Compose-by-`mutableStateOf` gotcha — the two imports are needed even though
nothing in the file appears to call them directly.

**Fix applied**: added both imports to `Theme.kt`. No other file in the
project uses the `by mutableStateOf` / `by remember` pattern (verified by
grep), so this was the only occurrence.

**Status**: fix has been applied and pushed for re-verification, but as of
this PROGRESS.md update **the corrected build has not yet come back green
from CI**. Do not assume Session 1 is fully done until the next Actions run
confirms `assembleDebug` succeeds — check that before starting Session 2,
and if it fails again, paste the new log back the same way as this one.

---

## 7. Known Issues / Carried-Over TODOs
- **[Fixed, pending re-verification]** First CI run failed on
  `Theme.kt:26` — missing `androidx.compose.runtime.getValue` /
  `androidx.compose.runtime.setValue` imports needed for the
  `var isDark by mutableStateOf(false)` delegate. Fix applied (imports
  added). First priority for whoever runs Session 2: confirm the next
  GitHub Actions run is actually green — this has not been re-confirmed
  by CI yet, only fixed based on reading the error.
- `toggle-label` letter-spacing uses an approximated static sp value, not
  true em-relative spacing (see Section 4).
- Only a partial slice of the full CSS's typography and spacing tokens has
  been extracted (only the ones explicitly listed in 01-SETUP.md's
  PROGRESS-TEMPLATE seed). The rest of the CSS (buttons, popups, sliders,
  dock, history grid items, clock, zoom controls, etc.) has many more
  hardcoded pixel values not yet pulled into tokens — expected, since this
  session's scope was tokens + theme system only, not full extraction of
  every value in the file. Later sessions building each component should
  extract that component's exact values into `Tokens.kt` at that time
  rather than approximating.
- No persistence layer decided yet (Section 5).
- minSdk 26 was a judgment call, not a requirement extracted from any
  provided source — flagged in Section 6, revisit if there's an actual
  target-device constraint.

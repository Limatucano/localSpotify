package br.com.localspotify.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimen(
    val zero: Dp = 0.dp,
    val xxsm: Dp = 2.dp,
    val xsm: Dp = 4.dp,
    val sm: Dp = 8.dp,
    val md: Dp = 16.dp,
    val xmd: Dp = 24.dp,
    val lg: Dp = 32.dp,
    val xlg: Dp = 48.dp,
    val xxlg: Dp = 54.dp,
    val huge: Dp = 64.dp,
    val xhuge: Dp = 84.dp,
    val xxhuge: Dp = 96.dp,
    val xxxhuge: Dp = 104.dp
)

val LocalDimen = compositionLocalOf {
    Dimen()
}

val MaterialTheme.dimen: Dimen
    @Composable
    @ReadOnlyComposable
    get() = LocalDimen.current
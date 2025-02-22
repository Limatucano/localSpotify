package br.com.localspotify.presentation.atomic.atom

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import br.com.localspotify.presentation.theme.dimen

@Composable
fun ColumnScope.Spacer(height: Dp = MaterialTheme.dimen.md) {
    Spacer(modifier = Modifier.height(height))
}

@Composable
fun RowScope.Spacer(width: Dp = MaterialTheme.dimen.md) {
    Spacer(modifier = Modifier.width(width))
}
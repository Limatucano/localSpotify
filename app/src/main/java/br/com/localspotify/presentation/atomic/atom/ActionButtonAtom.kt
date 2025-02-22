package br.com.localspotify.presentation.atomic.atom

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter

@Composable
fun ActionButtonAtom(
    modifier: Modifier = Modifier,
    contentDescription: String,
    icon: Painter,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Icon(
            tint = MaterialTheme.colorScheme.secondary,
            painter = icon,
            contentDescription = contentDescription
        )
    }
}
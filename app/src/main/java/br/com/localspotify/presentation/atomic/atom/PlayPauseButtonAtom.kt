package br.com.localspotify.presentation.atomic.atom

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import br.com.localspotify.R
import br.com.localspotify.presentation.theme.AppTheme
import br.com.localspotify.presentation.theme.dimen

@Composable
fun PlayPauseButtonAtom(
    modifier: Modifier = Modifier,
    size: Dp = MaterialTheme.dimen.xxlg,
    icon: Painter,
    onClick: () -> Unit
) {
    ActionButtonAtom(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary),
        icon = icon,
        contentDescription = "Play/Pause button"
    ) {
        onClick()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun PlayButtonAtomPreviewLight() {
    AppTheme {
        PlayPauseButtonAtom(
            icon = painterResource(R.drawable.ic_play),
            onClick = {}
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun PlayButtonAtomPreviewDark() {
    AppTheme {
        PlayPauseButtonAtom(
            icon = painterResource(R.drawable.ic_play),
            onClick = {}
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun PauseButtonAtomPreviewLight() {
    AppTheme {
        PlayPauseButtonAtom(
            icon = painterResource(R.drawable.ic_pause),
            onClick = {}
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun PauseButtonAtomPreviewDark() {
    AppTheme {
        PlayPauseButtonAtom(
            icon = painterResource(R.drawable.ic_pause),
            onClick = {}
        )
    }
}
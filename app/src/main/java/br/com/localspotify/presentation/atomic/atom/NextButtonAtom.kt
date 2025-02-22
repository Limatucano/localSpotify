package br.com.localspotify.presentation.atomic.atom

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.localspotify.R
import br.com.localspotify.presentation.theme.AppTheme
import br.com.localspotify.presentation.theme.dimen

@Composable
fun NextButtonAtom(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    ActionButtonAtom(
        modifier = modifier
            .size(MaterialTheme.dimen.xlg),
        icon = painterResource(R.drawable.ic_next),
        contentDescription = "Next button"
    ) {
        onClick()
    }
}

@Preview
@Composable
private fun NextButtonAtomPreviewLight() {
    AppTheme {
        NextButtonAtom(
            onClick = {}
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun NextButtonAtomPreviewDark() {
    AppTheme {
        NextButtonAtom(
            onClick = {}
        )
    }
}
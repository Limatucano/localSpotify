package br.com.localspotify.presentation.atomic.atom

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.localspotify.R
import br.com.localspotify.presentation.theme.AppTheme
import br.com.localspotify.presentation.theme.dimen

@Composable
fun AddButtonAtom(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    ActionButtonAtom(
        modifier = modifier
            .padding(MaterialTheme.dimen.sm)
            .size(MaterialTheme.dimen.lg)
            .clip(CircleShape)
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary
                ),
                shape = CircleShape
            ),
        icon = painterResource(R.drawable.ic_add),
        contentDescription = "Next button"
    ) {
        onClick()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun AddButtonAtomPreviewLight() {
    AppTheme {
        AddButtonAtom {}
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun AddButtonAtomPreviewDark() {
    AppTheme {
        AddButtonAtom {}
    }
}
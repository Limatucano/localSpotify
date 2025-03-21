package br.com.localspotify.presentation.atomic.molecule

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.localspotify.R
import br.com.localspotify.presentation.atomic.atom.CardImageAtom
import br.com.localspotify.presentation.atomic.atom.Spacer
import br.com.localspotify.presentation.atomic.atom.TextAtom
import br.com.localspotify.presentation.theme.AppTheme
import br.com.localspotify.presentation.theme.dimen

@Composable
fun CardItemMolecule(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String? = null,
    image: Painter? = null,
    onClickItem: () -> Unit = {}
) {
    Column(
        modifier = modifier.clickable { onClickItem() }
    ) {
        CardImageAtom(
            image = image
        )
        Spacer(MaterialTheme.dimen.xxsm)
        TextAtom(
            text = title,
            fontWeight = FontWeight.Bold,
            maxLines = 2
        )
        subtitle?.let {
            TextAtom(
                text = it,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun CardItemMoleculePreviewLightNoImage() {
    AppTheme {
        CardItemMolecule(
            title = "After Hours",
            subtitle = "The Weeknd"
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun CardItemMoleculePreviewLightImage() {
    AppTheme {
        CardItemMolecule(
            title = "After Hours",
            subtitle = "The Weeknd",
            image = painterResource(R.drawable.album_image_sample)
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun CardItemMoleculePreviewDarkNoImage() {
    AppTheme {
        CardItemMolecule(
            title = "After Hours",
            subtitle = "The Weeknd"
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun CardItemMoleculePreviewDarkImage() {
    AppTheme {
        CardItemMolecule(
            title = "After Hours",
            subtitle = "The Weeknd",
            image = painterResource(R.drawable.album_image_sample)
        )
    }
}
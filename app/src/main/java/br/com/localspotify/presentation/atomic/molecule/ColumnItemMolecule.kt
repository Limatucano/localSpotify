package br.com.localspotify.presentation.atomic.molecule

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import br.com.localspotify.R
import br.com.localspotify.presentation.atomic.atom.ActionButtonAtom
import br.com.localspotify.presentation.atomic.atom.CardImageAtom
import br.com.localspotify.presentation.atomic.atom.Spacer
import br.com.localspotify.presentation.atomic.atom.TextAtom
import br.com.localspotify.presentation.theme.AppTheme
import br.com.localspotify.presentation.theme.dimen

@Composable
fun ColumnItemMolecule(
    modifier: Modifier = Modifier,
    title: String,
    isAlreadySaved: Boolean = false,
    image: Painter? = null,
    onClickItem: () -> Unit,
    onClickSave: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClickItem() },
    ) {
        CardImageAtom(
            image = image,
            height = MaterialTheme.dimen.huge,
            width = MaterialTheme.dimen.huge
        )
        Spacer()
        Column(
            modifier = Modifier.weight(1f)
        ) {
            TextAtom(
                text = title,
                fontWeight = FontWeight.Bold
            )
        }
        if (!isAlreadySaved) {
            ActionButtonAtom(
                modifier = Modifier.align(Alignment.CenterVertically),
                icon = painterResource(R.drawable.ic_heart),
                contentDescription = "",
                onClick = { onClickSave() }
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun ColumnItemMoleculePreview() {
    AppTheme {
        ColumnItemMolecule(
            title = "After Hours",
            image = painterResource(R.drawable.album_image_sample),
            onClickItem = {},
            onClickSave = {}
        )
    }
}
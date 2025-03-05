package br.com.localspotify.presentation.atomic.organism

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import br.com.localspotify.R
import br.com.localspotify.domain.entity.Music
import br.com.localspotify.presentation.atomic.atom.Spacer
import br.com.localspotify.presentation.atomic.atom.TextAtom
import br.com.localspotify.presentation.atomic.molecule.CardItemMolecule
import br.com.localspotify.presentation.atomic.molecule.ColumnItemMolecule
import br.com.localspotify.presentation.theme.AppTheme
import br.com.localspotify.presentation.theme.dimen

@Composable
fun SavedMusicListOrganism(
    music: List<Music>,
    onClickItem: (Music) -> Unit
) {
    Column(
        modifier = Modifier.padding(MaterialTheme.dimen.md)
    ) {
        TextAtom(
            text = stringResource(R.string.home_saved_music),
            fontWeight = FontWeight.Bold,
            textStyle = MaterialTheme.typography.bodyLarge
        )
        Spacer()
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimen.md)
        ) {
            items(music) { item ->
                CardItemMolecule(
                    title = item.title,
                    subtitle = item.artist,
                    onClickItem = { onClickItem(item) }
                )
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun SavedMusicListOrganismPreview() {
    AppTheme {
        SavedMusicListOrganism(
            music = listOf(
                Music(
                    title = "Teste 1",
                    artist = "TESTE ARTISTA 1"
                ),
                Music(
                    title = "Teste 2",
                    artist = "TESTE ARTISTA 2"
                )
            ),
            onClickItem = {}
        )
    }
}
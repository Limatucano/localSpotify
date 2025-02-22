package br.com.localspotify.presentation.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.localspotify.presentation.atomic.atom.TextAtom

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        TextAtom(text = "TESTANDO TELA")
    }
}
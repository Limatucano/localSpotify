package br.com.localspotify.presentation.screen.home

import br.com.localspotify.domain.entity.Music

data class HomeUIState(
    val rawMusic: List<Music> = emptyList(),
    val savedMusic: List<Music> = emptyList()
)

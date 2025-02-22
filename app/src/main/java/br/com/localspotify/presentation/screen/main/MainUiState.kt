package br.com.localspotify.presentation.screen.main

import br.com.localspotify.domain.entity.Music

data class MainUiState(
    val currentMusic: Music? = null
)

package br.com.localspotify.presentation.screen.player

import br.com.localspotify.domain.entity.Music

data class PlayerUIState(
    val duration: Long = 0L,
    val progress: Long = 0L,
    val isPlaying: Boolean = false,
    val currentSelectedAudio: Music? = null
)
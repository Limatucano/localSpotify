package br.com.localspotify.data.player.service

import androidx.media3.common.MediaItem

sealed class AudioState {
    data object IDLE: AudioState()
    data class Ready(val duration: Long): AudioState()
    data class Progress(val progress: Long): AudioState()
    data class Buffering(val progress: Long): AudioState()
    data class Playing(val isPlaying: Boolean): AudioState()
    data class CurrentPlaying(
        val mediaItem: MediaItem,
        val duration: Long
    ): AudioState()
}
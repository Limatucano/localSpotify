package br.com.localspotify.data.player.service

sealed class PlayerEvent {
    data object PlayPause: PlayerEvent()
    data object SeekToNext: PlayerEvent()
    data object SeekToPrevious: PlayerEvent()
    data class UpdateProgress(val newProgress: Long): PlayerEvent()
}
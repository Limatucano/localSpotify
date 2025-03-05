package br.com.localspotify.presentation.screen.player

sealed class PlayerUiEvents{
    data object PlayPause: PlayerUiEvents()
    data object SeekToNext: PlayerUiEvents()
    data object Backward: PlayerUiEvents()
    data object Forward: PlayerUiEvents()
    data class SelectedAudioChange(val index: Int): PlayerUiEvents()
    data class SeekTo(val position: Float): PlayerUiEvents()
    data class UpdateProgress(val newProgress: Long): PlayerUiEvents()
}
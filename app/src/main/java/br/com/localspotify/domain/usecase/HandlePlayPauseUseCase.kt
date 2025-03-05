package br.com.localspotify.domain.usecase

import br.com.localspotify.data.player.service.PlayerEvent
import br.com.localspotify.domain.entity.Music
import br.com.localspotify.domain.repository.PlayerRepository

class HandlePlayPauseUseCase(
    private val playerRepository: PlayerRepository
) {

    operator fun invoke(music: Music? = null) {
        music?.let {
            val currentMusicPlaying = playerRepository.currentMusic()
            if (it.id != currentMusicPlaying?.id) {
                playerRepository.addMusic(it)
            }
        }
        playerRepository.handlePlayerEvent(PlayerEvent.PlayPause)
    }
}
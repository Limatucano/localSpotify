package br.com.localspotify.domain.usecase

import br.com.localspotify.data.player.service.PlayerEvent
import br.com.localspotify.domain.repository.PlayerRepository

class SeekToUseCase(
    private val repository: PlayerRepository
) {
    operator fun invoke(toNext: Boolean) {
        val event = if (toNext)
            PlayerEvent.SeekToNext
         else
             PlayerEvent.SeekToPrevious

        repository.handlePlayerEvent(event)
    }
}
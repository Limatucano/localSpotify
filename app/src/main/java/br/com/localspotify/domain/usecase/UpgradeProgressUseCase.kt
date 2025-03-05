package br.com.localspotify.domain.usecase

import br.com.localspotify.data.player.service.PlayerEvent
import br.com.localspotify.domain.repository.PlayerRepository

class UpgradeProgressUseCase(
    private val repository: PlayerRepository
) {
    operator fun invoke(newProgress: Long) {
        repository.handlePlayerEvent(PlayerEvent.UpdateProgress(newProgress))
    }
}
package br.com.localspotify.domain.usecase

import br.com.localspotify.data.player.service.AudioState
import br.com.localspotify.domain.repository.PlayerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AudioStateListenerUseCase(
    private val repository: PlayerRepository
) {
    operator fun invoke(): Flow<AudioState> = flow {
        repository.audioStateObserver().collect {
            emit(it)
        }
    }
}
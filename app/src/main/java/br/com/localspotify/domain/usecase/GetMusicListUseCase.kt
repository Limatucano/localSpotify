package br.com.localspotify.domain.usecase

import br.com.localspotify.domain.entity.Music
import br.com.localspotify.domain.repository.PlayerRepository

class GetMusicListUseCase(
    private val repository: PlayerRepository
) {
    suspend operator fun invoke(): List<Music> {
        return try {
            repository.getAllMusic()
        } catch (e: Exception) {
            emptyList()
        }
    }
}
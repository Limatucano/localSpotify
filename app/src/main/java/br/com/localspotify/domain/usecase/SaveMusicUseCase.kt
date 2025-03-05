package br.com.localspotify.domain.usecase

import br.com.localspotify.domain.entity.Music
import br.com.localspotify.domain.repository.PlayerRepository

class SaveMusicUseCase(
    private val repository: PlayerRepository
) {

    suspend operator fun invoke(music: Music) : Result<Any>{
        return try {
            Result.success(repository.saveMusic(music))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
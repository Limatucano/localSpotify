package br.com.localspotify.domain.usecase

import br.com.localspotify.domain.entity.Music
import br.com.localspotify.domain.repository.PlayerRepository

class GetRawMusicUseCase(
    private val repository: PlayerRepository,
    private val getMusicListUseCase: GetMusicListUseCase
) {
    suspend operator fun invoke(): List<Music> {
        return try {
            val savedMusicList = getMusicListUseCase()
            val savedIds = savedMusicList.map { it.id }.toSet()
            val rawMusicList = repository.getRawMusic()

            rawMusicList.map { music ->
                music.copy(
                    isAlreadySaved = music.id in savedIds
                )
            }
        } catch (e: Exception) {
            listOf()
        }
    }
}
package br.com.localspotify.domain.repository

import br.com.localspotify.data.player.service.AudioState
import br.com.localspotify.data.player.service.PlayerEvent
import br.com.localspotify.domain.entity.Music
import kotlinx.coroutines.flow.Flow

interface PlayerRepository {
    suspend fun getAllMusic(): List<Music>
    suspend fun getRawMusic(): List<Music>
    fun currentMusic(): Music?
    fun addMusic(music: Music)
    suspend fun saveMusic(music: Music)
    suspend fun getMusic(id: Long): Music?
    fun handlePlayerEvent(event: PlayerEvent)
    suspend fun audioStateObserver(): Flow<AudioState>
}
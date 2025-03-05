package br.com.localspotify.data.repository

import androidx.annotation.OptIn
import androidx.media3.common.util.UnstableApi
import br.com.localspotify.data.datasource.local.PlayerDataSource
import br.com.localspotify.data.datasource.room.dao.PlayerDao
import br.com.localspotify.data.player.service.AudioController
import br.com.localspotify.data.player.service.AudioState
import br.com.localspotify.data.player.service.PlayerEvent
import br.com.localspotify.domain.entity.Music
import br.com.localspotify.domain.mapper.toEntity
import br.com.localspotify.domain.mapper.toEntityList
import br.com.localspotify.domain.mapper.toMediaItem
import br.com.localspotify.domain.mapper.toModel
import br.com.localspotify.domain.mapper.toRawMusic
import br.com.localspotify.domain.repository.PlayerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class PlayerRepositoryImpl(
    private val dataSource: PlayerDataSource,
    private val playerDao: PlayerDao,
    private val audioController: AudioController
): PlayerRepository {
    override suspend fun getAllMusic(): List<Music> {
        return playerDao.getAll().toEntityList()
    }

    override suspend fun getRawMusic(): List<Music> = withContext(Dispatchers.IO){
        dataSource.getRawMusic().toEntityList()
    }

    @OptIn(UnstableApi::class)
    override fun currentMusic(): Music? {
        val currentMediaItem = audioController.currentMediaItem()
        return currentMediaItem?.toRawMusic(currentMediaItem.mediaMetadata.durationMs ?: 0L)
    }

    override fun addMusic(music: Music) {
        audioController.addMediaItem(music.toMediaItem())
    }

    override suspend fun saveMusic(music: Music) {
        playerDao.insert(music.toModel())
    }

    override suspend fun getMusic(id: Long): Music? {
        return playerDao.get(id)?.toEntity()
    }

    override fun handlePlayerEvent(event: PlayerEvent) {
        when(event) {
            is PlayerEvent.SeekToPrevious ->  audioController.onPlayerEvents(PlayerEvent.SeekToPrevious)
            is PlayerEvent.SeekToNext -> audioController.onPlayerEvents(PlayerEvent.SeekToNext)
            is PlayerEvent.UpdateProgress -> audioController.onPlayerEvents(PlayerEvent.UpdateProgress(event.newProgress))
            is PlayerEvent.PlayPause -> audioController.onPlayerEvents(PlayerEvent.PlayPause)
        }
    }

    override suspend fun audioStateObserver(): Flow<AudioState> = flow {
        audioController.state.collect {
            emit(it)
        }
    }
}
package br.com.localspotify.data.repository

import br.com.localspotify.data.datasource.local.PlayerDataSource
import br.com.localspotify.domain.entity.Music
import br.com.localspotify.domain.mapper.toEntity
import br.com.localspotify.domain.repository.PlayerRepository

class PlayerRepositoryImpl(
    private val dataSource: PlayerDataSource
): PlayerRepository {
    override suspend fun getAllMusic(): List<Music> {
        return dataSource.getAllMusic().toEntity()
    }
}
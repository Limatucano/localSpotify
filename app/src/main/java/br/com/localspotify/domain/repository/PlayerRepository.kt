package br.com.localspotify.domain.repository

import br.com.localspotify.domain.entity.Music

interface PlayerRepository {
    suspend fun getAllMusic(): List<Music>
    suspend fun getRawMusic(): List<Music>
    fun currentMusic(): Music?
    fun handlePlayPause()
    fun addMusic(music: Music)
    suspend fun saveMusic(music: Music)
    suspend fun getMusic(id: Long): Music?
}
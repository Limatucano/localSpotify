package br.com.localspotify.domain.repository

import br.com.localspotify.domain.entity.Music

interface PlayerRepository {
    suspend fun getAllMusic(): List<Music>
}
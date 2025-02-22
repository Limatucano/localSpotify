package br.com.localspotify.data.datasource.local

import br.com.localspotify.data.dto.MusicDTO

interface PlayerDataSource {
    suspend fun getAllMusic(): List<MusicDTO>
}
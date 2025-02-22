package br.com.localspotify.data.datasource.local

import br.com.localspotify.data.dto.MusicDTO

class PlayerDataSourceImpl : PlayerDataSource {
    override suspend fun getAllMusic(): List<MusicDTO> {
        return listOf(
            MusicDTO(
                imagePath = null,
                title = "After Hours",
                artist = "The Weeknd",
                filePath = ""
            )
        )
    }
}
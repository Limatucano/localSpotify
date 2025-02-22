package br.com.localspotify.domain.mapper

import br.com.localspotify.data.dto.MusicDTO
import br.com.localspotify.domain.entity.Music

fun MusicDTO.toEntity(): Music {
    return Music(
        imagePath = imagePath,
        title = title,
        artist = artist,
        filePath = filePath
    )
}

fun List<MusicDTO>.toEntity(): List<Music> {
    return this.map { it.toEntity() }
}
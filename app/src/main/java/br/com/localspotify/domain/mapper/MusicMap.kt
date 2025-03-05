package br.com.localspotify.domain.mapper

import androidx.core.net.toUri
import br.com.localspotify.data.dto.MusicDTO
import br.com.localspotify.data.dto.MusicModel
import br.com.localspotify.domain.entity.Music

fun MusicDTO.toEntity(): Music {
    return Music(
        uri = uri,
        title = title,
        id = id,
        artist = artist,
        duration = duration
    )
}

fun List<MusicDTO>.toEntityList(): List<Music> = this.map { it.toEntity() }

fun MusicModel.toEntity(): Music {
    return Music(
        uri = uri.toUri(),
        title = title,
        id = id,
        artist = artist,
        duration = duration
    )
}

@JvmName("MusicModelToEntity")
fun List<MusicModel>.toEntityList(): List<Music> = this.map { it.toEntity() }

fun Music.toModel(): MusicModel {
    return MusicModel(
        id = id,
        uri = uri.toString(),
        title = title,
        imagePath = imagePath,
        artist = artist,
        duration = duration
    )
}
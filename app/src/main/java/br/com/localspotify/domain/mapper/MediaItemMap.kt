package br.com.localspotify.domain.mapper

import androidx.annotation.OptIn
import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.util.UnstableApi
import br.com.localspotify.domain.entity.Music

@OptIn(UnstableApi::class)
fun MediaItem.toRawMusic(duration: Long): Music {
    return Music(
        id = this.mediaId.toLongOrNull() ?: 0L,
        uri = this.localConfiguration?.uri ?: "".toUri(),
        title = this.mediaMetadata.displayTitle.toString(),
        artist = this.mediaMetadata.subtitle.toString(),
        duration = duration
    )
}

fun Music.toMediaItem(): MediaItem {
    return MediaItem.Builder()
        .setUri(this.uri)
        .setMediaId(this.id.toString())
        .setMediaMetadata(
            MediaMetadata.Builder()
                .setDisplayTitle(this.title)
                .setSubtitle(this.artist)
                .build()
        )
        .build()
}
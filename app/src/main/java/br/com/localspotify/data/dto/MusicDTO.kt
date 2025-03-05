package br.com.localspotify.data.dto

import android.net.Uri

data class MusicDTO(
    val uri: Uri,
    val title: String,
    val id: Long,
    val artist: String,
    val duration: Long
)

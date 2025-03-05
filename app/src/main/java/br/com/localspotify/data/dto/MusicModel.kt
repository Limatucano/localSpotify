package br.com.localspotify.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "music")
data class MusicModel(
    @PrimaryKey val id: Long,
    val uri: String,
    val imagePath: String?,
    val title: String,
    val artist: String,
    val duration: Long
)
package br.com.localspotify.domain.entity

import android.net.Uri
import android.os.Parcelable
import androidx.core.net.toUri
import kotlinx.parcelize.Parcelize

@Parcelize
data class Music(
    val uri: Uri = "".toUri(),
    val title: String = "",
    val id: Long = 0L,
    val artist: String = "",
    val duration: Long = 0L,
    val imagePath: String? = null,
    val isAlreadySaved: Boolean = false
) : Parcelable

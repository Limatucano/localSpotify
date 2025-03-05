package br.com.localspotify.data.datasource.local

import android.content.ContentUris
import android.content.Context
import android.media.MediaScannerConnection
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.annotation.WorkerThread
import br.com.localspotify.data.dto.MusicDTO
import java.io.File

class PlayerDataSourceImpl(
    private val context: Context
) : PlayerDataSource {
    private fun indexingFiles() {
        val musicDir = File(Environment.getExternalStorageDirectory(), "Music")

        if (!musicDir.exists() || !musicDir.isDirectory) return

        val musicFiles = musicDir.listFiles { file -> file.isFile } ?: emptyArray()

        if (musicFiles.isEmpty()) return

        val filePaths = musicFiles.map { it.absolutePath }.toTypedArray()
        val mimeTypes = musicFiles.map { getMimeType(it) }.toTypedArray()

        MediaScannerConnection.scanFile(context, filePaths, mimeTypes) { path, uri ->
            Log.d("MediaScan", "Arquivo indexado: $path -> $uri")
        }
    }

    private fun getMimeType(file: File): String? {
        return when (file.extension.lowercase()) {
            "mp3" -> "audio/mp3"
            "wav" -> "audio/wav"
            "m4a" -> "audio/mp4"
            "flac" -> "audio/flac"
            "ogg" -> "audio/ogg"
            "aac" -> "audio/aac"
            "mp4" -> "video/mp4"
            "mkv" -> "video/x-matroska"
            else -> null
        }
    }

    @WorkerThread
    override suspend fun getRawMusic(): List<MusicDTO> {
        indexingFiles()
        val rawMusicDTOList = mutableListOf<MusicDTO>()
        val columns = arrayOf(
            MediaStore.Audio.AudioColumns._ID,
            MediaStore.Audio.AudioColumns.ARTIST,
            MediaStore.Audio.AudioColumns.DURATION,
            MediaStore.Audio.AudioColumns.TITLE
        )

        val cursor = context.contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            columns,
            null,
            null,
            "${MediaStore.Audio.AudioColumns.DISPLAY_NAME} ASC"
        )

        cursor?.use {
            val idIndex = it.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns._ID)
            val artistIndex = it.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.ARTIST)
            val durationIndex = it.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.DURATION)
            val titleIndex = it.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.TITLE)

            it.apply {
                while (moveToNext()) {
                    val id = getLong(idIndex)
                    val artist = getString(artistIndex)
                    val duration = getInt(durationIndex)
                    val title = getString(titleIndex)
                    val uri = ContentUris.withAppendedId(
                        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                        id
                    )
                    rawMusicDTOList.add(
                        MusicDTO(
                            uri = uri,
                            title = title,
                            id = id,
                            artist = artist,
                            duration = duration.toLong()
                        )
                    )
                }
            }
        }
        return rawMusicDTOList
    }
}
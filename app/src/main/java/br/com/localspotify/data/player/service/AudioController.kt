package br.com.localspotify.data.player.service

import android.annotation.SuppressLint
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class AudioController(
    private val exoplayer: ExoPlayer
): Player.Listener {
    private val _state: MutableStateFlow<AudioState> = MutableStateFlow(AudioState.IDLE)
    val state: StateFlow<AudioState> = _state.asStateFlow()

    init {
        exoplayer.addListener(this)
    }

    private var serviceScope = CoroutineScope(Dispatchers.Main + Job())

    fun addMediaItem(mediaItem: MediaItem) {
        exoplayer.setMediaItem(mediaItem)
        exoplayer.prepare()
    }

    fun currentMediaItem() = exoplayer.currentMediaItem

    fun addMediaItemList(mediaItems: List<MediaItem>) {
        exoplayer.setMediaItems(mediaItems)
        exoplayer.prepare()
    }

    fun onPlayerEvents(
        playerEvent: PlayerEvent
    ) {
        when(playerEvent) {
            is PlayerEvent.SeekToNext -> exoplayer.seekToNext()
            is PlayerEvent.SeekToPrevious -> exoplayer.seekToPrevious()
            is PlayerEvent.PlayPause -> manageControl()
            is PlayerEvent.UpdateProgress -> {
                exoplayer.seekTo(playerEvent.newProgress)
            }
        }
    }

    @SuppressLint("SwitchIntDef")
    override fun onPlaybackStateChanged(playbackState: Int) {
         when(playbackState) {
            ExoPlayer.STATE_BUFFERING -> _state.value = AudioState.Buffering(exoplayer.currentPosition)
            ExoPlayer.STATE_READY -> _state.value = AudioState.Ready(exoplayer.duration)
        }
    }

    override fun onIsPlayingChanged(isPlaying: Boolean) {
        _state.value = AudioState.Playing(isPlaying)
        _state.value = AudioState.CurrentPlaying(
            mediaItem = exoplayer.getMediaItemAt(exoplayer.currentMediaItemIndex),
            duration = exoplayer.duration
        )
        if (isPlaying) {
            startProgressUpdate()
        } else {
            stopProgressUpdate()
        }
    }

    private fun manageControl() {
        if (exoplayer.isPlaying) {
            exoplayer.pause()
            stopProgressUpdate()
            return
        }
        exoplayer.play()
        _state.value = AudioState.Playing(isPlaying = true)
        startProgressUpdate()
    }

    private fun startProgressUpdate() {
        serviceScope = if (serviceScope.isActive) serviceScope else CoroutineScope(Dispatchers.Main + Job())
        serviceScope.launch {
            while (isActive) {
                delay(100)
                _state.value = AudioState.Progress(exoplayer.currentPosition)
            }
        }
    }

    private fun stopProgressUpdate() {
       serviceScope.cancel()
        _state.value = AudioState.Playing(isPlaying = false)
    }
}
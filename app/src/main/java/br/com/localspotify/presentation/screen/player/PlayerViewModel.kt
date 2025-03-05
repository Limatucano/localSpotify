package br.com.localspotify.presentation.screen.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.localspotify.data.player.service.AudioController
import br.com.localspotify.data.player.service.AudioState
import br.com.localspotify.data.player.service.PlayerEvent
import br.com.localspotify.domain.mapper.toRawMusic
import br.com.localspotify.domain.usecase.AudioStateListenerUseCase
import br.com.localspotify.domain.usecase.HandlePlayPauseUseCase
import br.com.localspotify.domain.usecase.SeekToUseCase
import br.com.localspotify.domain.usecase.UpgradeProgressUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlayerViewModel(
    private val handlePlayPauseUseCase: HandlePlayPauseUseCase,
    private val seekToUseCase: SeekToUseCase,
    private val upgradeProgressUseCase: UpgradeProgressUseCase,
    private val audioStateListenerUseCase: AudioStateListenerUseCase
): ViewModel() {

    private val _uiState: MutableStateFlow<PlayerUIState> = MutableStateFlow(PlayerUIState())
    val uiState: StateFlow<PlayerUIState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            audioStateListenerUseCase().collectLatest { state ->
                when (state) {
                    is AudioState.IDLE -> Unit
                    is AudioState.Buffering -> calculateProgress(state.progress)
                    is AudioState.Playing -> {
                        _uiState.update { uiState ->
                            uiState.copy(
                                isPlaying = state.isPlaying
                            )
                        }
                    }
                    is AudioState.Progress -> calculateProgress(state.progress)
                    is AudioState.CurrentPlaying -> {
                        _uiState.update { uiState ->
                            uiState.copy(
                                currentSelectedAudio = state.mediaItem.toRawMusic(state.duration)
                            )
                        }
                    }
                    is AudioState.Ready -> {
                        _uiState.update { uiState ->
                            uiState.copy(
                                duration = state.duration
                            )
                        }
                    }
                }
            }
        }
    }

    fun updateProgress(newProgress: Long) {
        upgradeProgressUseCase(newProgress).also {
            _uiState.update { state ->
                state.copy(
                    progress = newProgress
                )
            }
        }
    }

    fun playPause() {
        handlePlayPauseUseCase()
    }

    fun seekToNext() {
        seekToUseCase(toNext = true)
    }

    fun seekToPrevious() {
        seekToUseCase(toNext = false)
    }

    private fun calculateProgress(currentProgress: Long) {
        _uiState.update { state ->
            state.copy(
                progress = currentProgress,
            )
        }
    }
}
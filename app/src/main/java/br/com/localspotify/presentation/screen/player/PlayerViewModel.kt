package br.com.localspotify.presentation.screen.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.localspotify.data.player.service.AudioController
import br.com.localspotify.data.player.service.AudioState
import br.com.localspotify.data.player.service.PlayerEvent
import br.com.localspotify.domain.mapper.toRawMusic
import br.com.localspotify.domain.usecase.HandlePlayPauseUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlayerViewModel(
    private val audioServiceHandler: AudioController,
    private val handlePlayPauseUseCase: HandlePlayPauseUseCase
): ViewModel() {

    private val _uiState: MutableStateFlow<PlayerUIState> = MutableStateFlow(PlayerUIState())
    val uiState: StateFlow<PlayerUIState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            audioServiceHandler.state.collectLatest { state ->
                when (state) {
                    is AudioState.IDLE -> {}
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

    fun onUiEvents(uiEvents: PlayerUiEvents) {
        viewModelScope.launch {
            when(uiEvents) {
                is PlayerUiEvents.Backward -> audioServiceHandler.onPlayerEvents(PlayerEvent.Backward)
                is PlayerUiEvents.Forward -> audioServiceHandler.onPlayerEvents(PlayerEvent.Forward)
                is PlayerUiEvents.SeekToNext -> audioServiceHandler.onPlayerEvents(PlayerEvent.SeekToNext)
                is PlayerUiEvents.PlayPause -> handlePlayPauseUseCase()
                is PlayerUiEvents.SeekTo -> audioServiceHandler.onPlayerEvents(
                    playerEvent = PlayerEvent.SeekTo,
                    seekPosition = ((_uiState.value.duration * uiEvents.position) / 100f).toLong()
                )
                is PlayerUiEvents.SelectedAudioChange -> audioServiceHandler.onPlayerEvents(
                    playerEvent = PlayerEvent.SelectedAudioChange,
                    selectedAudioIndex = uiEvents.index,
                )
                is PlayerUiEvents.UpdateProgress -> {
                    audioServiceHandler.onPlayerEvents(
                        playerEvent = PlayerEvent.UpdateProgress(uiEvents.newProgress)
                    )
                    _uiState.update { state ->
                        state.copy(
                            progress = uiEvents.newProgress
                        )
                    }
                }
            }
        }
    }

    private fun calculateProgress(currentProgress: Long) {
        _uiState.update { state ->
            state.copy(
                progress = currentProgress,
            )
        }
    }
}
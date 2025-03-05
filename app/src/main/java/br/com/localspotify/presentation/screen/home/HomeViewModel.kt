package br.com.localspotify.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.localspotify.domain.entity.Music
import br.com.localspotify.domain.usecase.GetMusicListUseCase
import br.com.localspotify.domain.usecase.GetRawMusicUseCase
import br.com.localspotify.domain.usecase.HandlePlayPauseUseCase
import br.com.localspotify.domain.usecase.SaveMusicUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val handlePlayPauseUseCase: HandlePlayPauseUseCase,
    private val getRawMusicUseCase: GetRawMusicUseCase,
    private val saveMusicUseCase: SaveMusicUseCase,
    private val getMusicListUseCase: GetMusicListUseCase
): ViewModel() {

    private val _uiState: MutableStateFlow<HomeUIState> = MutableStateFlow(HomeUIState())
    val uiState: StateFlow<HomeUIState> = _uiState.asStateFlow()

    fun loadData() {
        loadRawMusic()
        loadSavedMusic()
    }

    private fun loadRawMusic() {
        viewModelScope.launch(Dispatchers.IO) {
            val audioList = getRawMusicUseCase()
            _uiState.update { state ->
                state.copy(
                    rawMusic = audioList,
                )
            }
        }
    }

    private fun loadSavedMusic() {
        viewModelScope.launch(Dispatchers.IO) {
            val musicList = getMusicListUseCase()
            _uiState.update { state ->
                state.copy(
                    savedMusic = musicList
                )
            }
        }
    }

    fun handlePlayPause(music: Music) {
        handlePlayPauseUseCase(music)
    }

    fun onSaveMusic(music: Music) {
        viewModelScope.launch {
            saveMusicUseCase(music)
        }
    }
}
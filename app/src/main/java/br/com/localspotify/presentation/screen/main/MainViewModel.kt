package br.com.localspotify.presentation.screen.main

import br.com.localspotify.domain.repository.PlayerRepository
import br.com.localspotify.presentation.BaseViewModel

class MainViewModel(
    private val repository: PlayerRepository
): BaseViewModel<MainUiState>(
    initialUiState = MainUiState()
) {

}
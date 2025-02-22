package br.com.localspotify.di

import br.com.localspotify.presentation.screen.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

object PresentationModule: KoinModule {
    override val module = module {
        viewModelOf(::MainViewModel)
    }
}
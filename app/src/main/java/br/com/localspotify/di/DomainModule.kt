package br.com.localspotify.di

import br.com.localspotify.domain.usecase.GetMusicListUseCase
import br.com.localspotify.domain.usecase.GetRawMusicUseCase
import br.com.localspotify.domain.usecase.HandlePlayPauseUseCase
import br.com.localspotify.domain.usecase.SaveMusicUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

internal object DomainModule: KoinModule {
    override val module: Module = module {
        single { HandlePlayPauseUseCase(get()) }
        single { GetMusicListUseCase(get()) }
        single { SaveMusicUseCase(get()) }
        single { GetRawMusicUseCase(get(), get()) }
    }
}
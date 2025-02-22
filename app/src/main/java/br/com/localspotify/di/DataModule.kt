package br.com.localspotify.di

import br.com.localspotify.data.datasource.local.PlayerDataSource
import br.com.localspotify.data.datasource.local.PlayerDataSourceImpl
import br.com.localspotify.data.repository.PlayerRepositoryImpl
import br.com.localspotify.domain.repository.PlayerRepository
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal object DataModule: KoinModule {
    override val module: Module = module {

        singleOf(::PlayerDataSourceImpl) bind PlayerDataSource::class
        singleOf(::PlayerRepositoryImpl) bind PlayerRepository::class
    }
}
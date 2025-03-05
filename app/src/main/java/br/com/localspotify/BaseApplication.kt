package br.com.localspotify

import android.app.Application
import androidx.annotation.OptIn
import androidx.media3.common.util.UnstableApi
import br.com.localspotify.di.DataModule
import br.com.localspotify.di.DomainModule
import br.com.localspotify.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApplication: Application() {

    @OptIn(UnstableApi::class)
    override fun onCreate() {
        startKoin {
            androidLogger()
            androidContext(this@BaseApplication)
            modules(
                DataModule.module,
                DomainModule.module,
                PresentationModule.module
            )
        }
        super.onCreate()
    }
}
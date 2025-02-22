package br.com.localspotify

import android.app.Application
import br.com.localspotify.di.DataModule
import br.com.localspotify.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApplication: Application() {

    override fun onCreate() {
        startKoin {
            androidLogger()
            androidContext(this@BaseApplication)
            modules(
                DataModule.module,
                PresentationModule.module
            )
        }
        super.onCreate()
    }
}
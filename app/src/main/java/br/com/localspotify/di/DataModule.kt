package br.com.localspotify.di

import android.content.Context
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector
import androidx.media3.session.MediaSession
import androidx.room.Room
import br.com.localspotify.data.datasource.local.PlayerDataSource
import br.com.localspotify.data.datasource.local.PlayerDataSourceImpl
import br.com.localspotify.data.datasource.room.database.PlayerDatabase
import br.com.localspotify.data.player.notification.AudioNotificationManager
import br.com.localspotify.data.player.service.AudioController
import br.com.localspotify.data.repository.PlayerRepositoryImpl
import br.com.localspotify.domain.repository.PlayerRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

@UnstableApi
internal object DataModule: KoinModule {
    override val module: Module = module {

        single {
            PlayerDataSourceImpl(androidContext())
        } bind PlayerDataSource::class

        singleOf(::PlayerRepositoryImpl) bind PlayerRepository::class

        single {
            providesExoPlayerBuilder(androidContext())
        }

        single {
            AudioController(
                exoplayer = get<ExoPlayer>()
            )
        }

        single {
            providesNotificationManager(
                context = androidContext(),
                exoPlayer = get<ExoPlayer>()
            )
        }

        single {
            AudioNotificationManager(
                context = androidContext(),
                exoPlayer = get<ExoPlayer>()
            )
        }

        single {
            providesMediaSession(
                context = androidContext(),
                exoPlayer = get<ExoPlayer>()
            )
        }

        single {
            providesPlayerDatabase(
                context = androidContext()
            )
        }

        single {
            get<PlayerDatabase>().playerDao()
        }
    }

    private fun providesPlayerDatabase(context: Context): PlayerDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = PlayerDatabase::class.java,
            name = "player_database"
        ).build()
    }

    private fun providesNotificationManager(
        context: Context,
        exoPlayer: ExoPlayer
    ): AudioNotificationManager = AudioNotificationManager(
        context = context,
        exoPlayer = exoPlayer
    )

    private fun providesMediaSession(
        context: Context,
        exoPlayer: ExoPlayer
    ): MediaSession = MediaSession.Builder(context, exoPlayer).build()

    private fun providesAudioAttributes(): AudioAttributes {
        return AudioAttributes.Builder()
            .setContentType(C.AUDIO_CONTENT_TYPE_MOVIE)
            .setUsage(C.USAGE_MEDIA)
            .build()
    }

    @UnstableApi
    private fun providesExoPlayerBuilder(context: Context): ExoPlayer {
        return ExoPlayer.Builder(context)
            .setAudioAttributes(providesAudioAttributes(), true)
            .setHandleAudioBecomingNoisy(true)
            .setTrackSelector(DefaultTrackSelector(context))
            .build()
    }
}
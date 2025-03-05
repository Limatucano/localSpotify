package br.com.localspotify.data.datasource.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.localspotify.data.datasource.room.dao.PlayerDao
import br.com.localspotify.data.dto.MusicModel

@Database(entities = [MusicModel::class], version = 1)
abstract class PlayerDatabase: RoomDatabase() {
    abstract fun playerDao(): PlayerDao
}
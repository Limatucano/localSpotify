package br.com.localspotify.data.datasource.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.localspotify.data.dto.MusicModel

@Dao
interface PlayerDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(music: MusicModel)

    @Query("SELECT * FROM music")
    suspend fun getAll(): List<MusicModel>

    @Query("SELECT * FROM music WHERE id = :id")
    suspend fun get(id: Long): MusicModel?
}
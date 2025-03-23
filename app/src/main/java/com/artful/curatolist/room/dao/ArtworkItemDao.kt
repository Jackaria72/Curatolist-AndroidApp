package com.artful.curatolist.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.artful.curatolist.room.data.ArtworkItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ArtworkItemDao {

    @Insert
    suspend fun insert(item: ArtworkItem)

    @Delete
    suspend fun delete(item: ArtworkItem)

    @Query("SELECT * FROM artwork_items WHERE listId = :listId")
    fun getArtworkForList(listId: Long): Flow<List<ArtworkItem>>
}
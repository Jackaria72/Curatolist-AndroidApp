package com.artful.curatolist.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.artful.curatolist.room.data.ArtworkList
import com.artful.curatolist.room.data.ArtworkListWithArt
import kotlinx.coroutines.flow.Flow

@Dao
interface ArtworkListDao {

    @Insert
    suspend fun insert(list: ArtworkList)

    @Delete
    suspend fun delete(list: ArtworkList)

    @Query("SELECT * FROM artwork_lists")
    fun getAllLists(): Flow<List<ArtworkList>>

    @Transaction
    @Query("SELECT * FROM artwork_lists WHERE listId = :listId")
    fun getListWithArt(listId: Long): Flow<ArtworkListWithArt>
}
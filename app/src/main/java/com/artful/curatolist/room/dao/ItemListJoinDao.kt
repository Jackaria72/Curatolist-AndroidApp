package com.artful.curatolist.room.dao

import androidx.room.Dao
import androidx.room.Insert
import com.artful.curatolist.room.data.ArtListArtWorkCrossRef
import com.artful.curatolist.room.data.ArtworkItem

@Dao
interface ItemListJoinDao {

    @Insert
    suspend fun insertArtworkItemToList(artListArtWorkCrossRef: ArtListArtWorkCrossRef)
}
package com.artful.curatolist.room.repository

import com.artful.curatolist.room.data.ArtworkItem
import com.artful.curatolist.room.dao.ArtworkItemDao
import com.artful.curatolist.room.data.ArtworkList
import com.artful.curatolist.room.dao.ArtworkListDao
import com.artful.curatolist.room.dao.ItemListJoinDao
import com.artful.curatolist.room.data.ArtListArtWorkCrossRef

class RoomRepository(
    private val artworkListDao: ArtworkListDao,
    private val artworkItemDao: ArtworkItemDao,
    private val joinDao: ItemListJoinDao
) {
    var getAllLists = artworkListDao.getAllLists()

    fun getListWithItems(listId: Long) = artworkListDao.getListWithArt(listId)

    suspend fun insertList(list: ArtworkList) {
        artworkListDao.insert(list)
    }

    suspend fun insertArtworkItem(item: ArtworkItem) {
        artworkItemDao.insert(item)
    }
    suspend fun insertArtworkItemToList(artListArtWorkCrossRef: ArtListArtWorkCrossRef) {
        joinDao.insertArtworkItemToList(artListArtWorkCrossRef)
    }

    suspend fun deleteList(list: ArtworkList) {
        artworkListDao.delete(list)
    }

    suspend fun deleteArtworkItem(item: ArtworkItem) {
        artworkItemDao.delete(item)
    }
}
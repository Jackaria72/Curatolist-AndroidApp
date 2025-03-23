package com.artful.curatolist.room.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.artful.curatolist.room.data.ArtworkItem
import com.artful.curatolist.room.dao.ArtworkItemDao
import com.artful.curatolist.room.data.ArtworkList
import com.artful.curatolist.room.dao.ArtworkListDao
import com.artful.curatolist.room.data.ArtworkListWithArt
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class RoomRepository(
    private val artworkListDao: ArtworkListDao,
    private val artworkItemDao: ArtworkItemDao
) {
    var getAllLists = artworkListDao.getAllLists()

    fun getListWithItems(listId: Long) = artworkListDao.getListWithArt(listId)

    suspend fun insertList(list: ArtworkList) {
        artworkListDao.insert(list)
    }

    suspend fun insertArtworkItem(item: ArtworkItem) {
        artworkItemDao.insert(item)
    }

    suspend fun deleteList(list: ArtworkList) {
        artworkListDao.delete(list)
    }

    suspend fun deleteArtworkItem(item: ArtworkItem) {
        artworkItemDao.delete(item)
    }
}
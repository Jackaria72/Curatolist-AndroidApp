package com.artful.curatolist.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artful.curatolist.model.Artwork
import com.artful.curatolist.room.Graph
import com.artful.curatolist.room.data.ArtListArtWorkCrossRef
import com.artful.curatolist.room.data.ArtworkList
import com.artful.curatolist.room.data.ArtworkListWithArt
import com.artful.curatolist.room.repository.RoomRepository
import com.artful.curatolist.viewmodel.util.toArtworkItem
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ListViewModel(private val repository: RoomRepository = Graph.repository) : ViewModel() {

    var state by mutableStateOf(ListState())
        private set

    var listDetailsState by mutableStateOf(ListDetailsState())
        private set

    init {
        getLists()
    }

    private fun getLists(){
        viewModelScope.launch {
            repository.getAllLists.collectLatest {
                state = state.copy(
                    items = it
                )
            }
        }
    }
    fun getListWithItems(listId: Long) {
        viewModelScope.launch {
            repository.getListWithItems(listId).collectLatest { listWithItems ->
                listDetailsState = listDetailsState.copy(list = listWithItems)
            }
        }
    }

    fun addToFavourites(artwork: Artwork) {
        viewModelScope.launch {
            val artworkItem = artwork.toArtworkItem(1L)
            repository.insertArtworkItem(artworkItem)
            repository.insertArtworkItemToList(
                ArtListArtWorkCrossRef(artworkItem.artworkId, 1)
            )
        }
    }
}
data class ListState(
    val items: List<ArtworkList> = emptyList<ArtworkList>()
)
data class ListDetailsState(
    val list: ArtworkListWithArt = ArtworkListWithArt()
)
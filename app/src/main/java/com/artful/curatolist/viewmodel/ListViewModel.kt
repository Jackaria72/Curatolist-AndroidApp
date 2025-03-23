package com.artful.curatolist.viewmodel

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.artful.curatolist.model.Artwork
import com.artful.curatolist.repository.CuratolistRepository
import com.artful.curatolist.room.Graph
import com.artful.curatolist.room.data.ArtworkItem
import com.artful.curatolist.room.dao.ArtworkItemDao
import com.artful.curatolist.room.data.ArtworkList
import com.artful.curatolist.room.dao.ArtworkListDao
import com.artful.curatolist.room.data.ArtworkListWithArt
import com.artful.curatolist.room.database.CuratolistDatabase
import com.artful.curatolist.room.repository.RoomRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ListViewModel(private val repository: RoomRepository = Graph.repository) : ViewModel() {

    var state by mutableStateOf(ListState())
        private set

    var listDetailsState by mutableStateOf(ListDetailsState())
        private set


//    private val _list = mutableStateOf()
//    val list: MutableState<ArtworkList> = _list

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
}
data class ListState(
    val items: List<ArtworkList> = emptyList<ArtworkList>()
)
data class ListDetailsState(
    val list: ArtworkListWithArt = ArtworkListWithArt()
)
package com.artful.curatolist.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artful.curatolist.model.Artwork
import com.artful.curatolist.model.ResponsePage
import com.artful.curatolist.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArtworkViewModel: ViewModel() {
    private val _art = mutableStateOf(emptyList<Artwork>())
    val art: State<List<Artwork>> = _art
    private val _pageInfo = mutableStateOf<ResponsePage.PageInfo?>(null)
    val pageInfo: State<ResponsePage.PageInfo?> = _pageInfo

    private val _currentAppPage = mutableIntStateOf(0)
    val currentAppPage: State<Int> = _currentAppPage

    private val _paginatedArtwork = MutableStateFlow(emptyList<Artwork>())
    val paginatedArtwork: StateFlow<List<Artwork>> = _paginatedArtwork

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    var pageLimit = 20

    init {
        getArtList()
    }

    fun getArtList(){
        viewModelScope.launch {
            _isLoading.value = true
            val apiService = RetrofitInstance.api
            try {
                val response = apiService.getArt()

                response.let {
                    _art.value = it.artwork
                    _pageInfo.value = it.pageInfo
                    updatePaginatedList()
                    _isLoading.value = false
                }
            }
            catch (e: Exception) {
                Log.e("Http Error","Unable to fetch Artwork: ${e.message}")
                _art.value = emptyList<Artwork>()
            }
        }
    }

    fun updatePaginatedList() {
        val start = _currentAppPage.intValue * pageLimit
        val end = (start + pageLimit).coerceAtMost(_art.value.size)
        _paginatedArtwork.value = _art.value.subList(start, end)
    }

    fun nextPage() {
        if((_currentAppPage.intValue + 1) * pageLimit < _art.value.size) {
            _currentAppPage.intValue = _currentAppPage.intValue.plus(1)
            updatePaginatedList()
        }
    }

    fun previousPage() {
        if(_currentAppPage.intValue > 0) {
            _currentAppPage.intValue = _currentAppPage.intValue.minus(1)
            updatePaginatedList()
        }
    }
}
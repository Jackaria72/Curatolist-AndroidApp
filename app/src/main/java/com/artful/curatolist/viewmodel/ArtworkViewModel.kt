package com.artful.curatolist.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artful.curatolist.model.Artwork
import com.artful.curatolist.model.ResponsePage
import com.artful.curatolist.repository.CuratolistRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArtworkViewModel(private  val repository: CuratolistRepository): ViewModel() {
    private val _art = mutableStateOf(emptyList<Artwork>())
    val art: State<List<Artwork>> = _art
    private val _pageInfo = mutableStateOf<ResponsePage.PageInfo?>(null)
    val pageInfo: State<ResponsePage.PageInfo?> = _pageInfo

    private val _currentAppPage = mutableIntStateOf(0)
    val currentAppPage: State<Int> = _currentAppPage

    private val _currentApiPage = mutableIntStateOf(1)
    val currentApiPage: State<Int> = _currentApiPage

    private val _paginatedArtwork = MutableStateFlow(emptyList<Artwork>())
    val paginatedArtwork: StateFlow<List<Artwork>> = _paginatedArtwork

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _q = mutableStateOf("")
    val q: MutableState<String> get() = _q

    var pageLimit = 20
    val apiQuery = if (q.value.isEmpty()) null else q.value

//    init {
//        getArtList(_currentApiPage.intValue, apiQuery)
//    }

    fun getArtList(page: Int, q: String?){
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.getArt(page, q)
                response.let {
                    _art.value = it.artwork
                    _pageInfo.value = it.pageInfo
                    _currentAppPage.intValue = 0
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
        } else if (_currentApiPage.intValue < (_pageInfo.value?.combinedPageTotal ?: 0)) {
            _currentApiPage.intValue = _currentApiPage.intValue.plus(1)
            getArtList(_currentApiPage.intValue, apiQuery)
        }
    }

    fun previousPage() {
        if(_currentAppPage.intValue > 0) {
            _currentAppPage.intValue = _currentAppPage.intValue.minus(1)
            updatePaginatedList()
        } else if (_currentApiPage.intValue > 1) {
            _currentApiPage.intValue = _currentApiPage.intValue.minus(1)
            getArtList(_currentApiPage.intValue, apiQuery)
        }
    }

    fun onQueryChange(newQuery: String) {
        _q.value = newQuery
    }

}
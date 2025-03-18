package com.artful.curatolist.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artful.curatolist.model.Artwork
import com.artful.curatolist.network.RetrofitInstance
import kotlinx.coroutines.launch

class ArtworkViewModel: ViewModel() {
    private val _art = mutableStateOf(emptyList<Artwork>())
    val art: State<List<Artwork>> = _art

    init {
        getArtList()
    }

    fun getArtList(){
        viewModelScope.launch {
            val apiService = RetrofitInstance.api
            try {
                _art.value = apiService.getArt()
            }
            catch (e: Exception) {
                Log.e("Http Error","Unable to fetch Artwork: ${e.message}")
                _art.value = emptyList<Artwork>()
            }
        }
    }
}
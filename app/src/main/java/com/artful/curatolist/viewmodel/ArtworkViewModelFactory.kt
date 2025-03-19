package com.artful.curatolist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.artful.curatolist.repository.CuratolistRepository

class ArtworkViewModelFactory(private val repository: CuratolistRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArtworkViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ArtworkViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
package com.karan.pratical

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ArtistViewModelFactory(private val artistRepo: ArtistRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ArtistViewModel(artistRepo) as T
    }
}
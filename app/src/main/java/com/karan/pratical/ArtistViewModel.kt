package com.karan.pratical

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArtistViewModel(private val artistRepo: ArtistRepo) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            artistRepo.getArtistData("jack johnson",50)
        }
    }

    val quotes : LiveData<ArtistData>
        get() = artistRepo.artist

}
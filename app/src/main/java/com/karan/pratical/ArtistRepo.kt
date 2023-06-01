package com.karan.pratical

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ArtistRepo(private val apiInterface: ApiInterface) {

    private val artistLiveData = MutableLiveData<ArtistData>()
    val artist : LiveData<ArtistData>
    get()= artistLiveData

    suspend fun getArtistData( term : String, limit : Int){
        val result = apiInterface.getArtistData(term,limit)
        if (result?.body() != null){
            artistLiveData.postValue(result.body())
        }
    }
}
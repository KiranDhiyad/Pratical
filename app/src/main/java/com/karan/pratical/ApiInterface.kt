package com.karan.pratical

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("search")
    suspend fun getArtistData(@Query("term") term : String,@Query ("limit") limit : Int) : Response<ArtistData>

}
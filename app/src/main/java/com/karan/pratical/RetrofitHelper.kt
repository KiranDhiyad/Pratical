package com.karan.pratical

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    val SERVER_URL = "https://itunes.apple.com/"

    fun getInstance() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}
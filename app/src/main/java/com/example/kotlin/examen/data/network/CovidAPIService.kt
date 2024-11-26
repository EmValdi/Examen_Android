package com.example.kotlin.examen.data.network

import com.example.kotlin.examen.data.network.model.CovidObject
import retrofit2.http.GET
import retrofit2.http.Query

interface CovidAPIService {
    //https://pokeapi.co/api/v2/pokemon/?limit=1279
    @GET("covid19")
    suspend fun getDayData(
        @Query("date") date:String
    ): CovidObject
}
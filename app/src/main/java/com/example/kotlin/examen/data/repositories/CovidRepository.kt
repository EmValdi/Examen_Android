package com.example.kotlin.examen.data.repositories

import com.example.kotlin.examen.data.network.CovidApiClient
import com.example.kotlin.examen.data.network.model.CovidObject

class CovidRepository {
    private val apiCovid = CovidApiClient()

    suspend fun getCovidList(date:String): CovidObject? = apiCovid.getCovidDay(date)
}
package com.example.kotlin.examen.data.repositories

import com.example.kotlin.examen.data.network.CovidApiClient
import com.example.kotlin.examen.data.network.model.CovidObject

class CovidRepository {
    private val apiCovid = CovidApiClient()

    /**
     * Llama al Api client y le da la fecha en formato de string para
     * recuperar los datos del dia
     */

    suspend fun getCovidList(date:String): CovidObject? = apiCovid.getCovidDay(date)
}